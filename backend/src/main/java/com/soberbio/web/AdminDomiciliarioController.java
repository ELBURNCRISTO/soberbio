package com.soberbio.web;

import com.soberbio.domain.Domiciliario;
import com.soberbio.repository.DomiciliarioRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/admin/domiciliarios")
@CrossOrigin
public class AdminDomiciliarioController {

  private final DomiciliarioRepository repo;

  public AdminDomiciliarioController(DomiciliarioRepository repo) {
    this.repo = repo;
  }

  @GetMapping
  public List<Domiciliario> list(
      @RequestParam(defaultValue = "true") boolean soloActivos,
      @RequestParam(defaultValue = "false") boolean soloDisponibles
  ) {
    if (soloActivos && soloDisponibles) {
      return repo.findByActivoTrueAndDisponibleTrue();
    }
    if (soloActivos) {
      return repo.findByActivoTrue();
    }
    return repo.findAll();
  }

  // cedula unica
  @PostMapping
  public ResponseEntity<?> create(@Valid @RequestBody Domiciliario d) {
    if (repo.existsByCedula(d.getCedula())) {
      return ResponseEntity.badRequest().body("La cédula ya existe");
    }
    Domiciliario saved = repo.save(d);
    return ResponseEntity.created(URI.create("/api/admin/domiciliarios/" + saved.getId())).body(saved);
  }

  //si cambia cedula verifica que no exista
  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Domiciliario d) {
    return repo.findById(id).map(old -> {
      if (!old.getCedula().equals(d.getCedula()) && repo.existsByCedula(d.getCedula())) {
        return ResponseEntity.badRequest().body("La cédula ya existe");
      }
      d.setId(id);
      return ResponseEntity.ok(repo.save(d));
    }).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deactivate(@PathVariable Long id) {
    var opt = repo.findById(id);
    if (opt.isEmpty()) return ResponseEntity.notFound().build();
    var dom = opt.get();
    dom.setActivo(false);
    repo.save(dom);
    return ResponseEntity.noContent().build();
  }
}