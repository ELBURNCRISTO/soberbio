package com.soberbio.web;

import com.soberbio.domain.Operador;
import com.soberbio.repository.OperadorRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/admin/operadores")
@CrossOrigin
public class AdminOperadorController {

  private final OperadorRepository repo;

  public AdminOperadorController(OperadorRepository repo) {
    this.repo = repo;
  }

  @GetMapping
  public List<Operador> list(@RequestParam(defaultValue = "true") boolean soloActivos) {
    return soloActivos ? repo.findByActivoTrue() : repo.findAll();
  }

  @PostMapping
  public ResponseEntity<?> create(@Valid @RequestBody Operador o) {
    if (repo.existsByUsuario(o.getUsuario())) {
      return ResponseEntity.badRequest().body("El usuario ya existe");
    }
    Operador saved = repo.save(o);
    return ResponseEntity.created(URI.create("/api/admin/operadores/" + saved.getId())).body(saved);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Operador o) {
    return repo.findById(id).map(old -> {
      // si cambia el usuario valida que no exista
      if (!old.getUsuario().equals(o.getUsuario()) && repo.existsByUsuario(o.getUsuario())) {
        return ResponseEntity.badRequest().body("El usuario ya existe");
      }
      o.setId(id);
      return ResponseEntity.ok(repo.save(o));
    }).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deactivate(@PathVariable Long id) {
    var opt = repo.findById(id);
    if (opt.isEmpty()) return ResponseEntity.notFound().build();
    var op = opt.get();
    op.setActivo(false);
    repo.save(op);
    return ResponseEntity.noContent().build();
  }
}