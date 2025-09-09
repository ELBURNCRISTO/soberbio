package com.soberbio.web;

import com.soberbio.domain.Adicional;
import com.soberbio.repository.AdicionalRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/adicionales")
@CrossOrigin
public class AdminAdicionalController {

  private final AdicionalRepository repo;
  public AdminAdicionalController(AdicionalRepository repo){ this.repo = repo; }

  @GetMapping
  public List<Adicional> list(@RequestParam(defaultValue = "true") boolean soloActivos) {
    return soloActivos ? repo.findByActivoTrue() : repo.findAll();
  }

  @PostMapping
  public ResponseEntity<Adicional> create(@Valid @RequestBody Adicional a) {
    return ResponseEntity.ok(repo.save(a));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Adicional> update(@PathVariable Long id, @Valid @RequestBody Adicional a) {
    return repo.findById(id).map(old -> {
      a.setId(id);
      return ResponseEntity.ok(repo.save(a));
    }).orElseGet(() -> ResponseEntity.notFound().build());
  }

@DeleteMapping("/{id}")
public ResponseEntity<Void> deactivate(@PathVariable Long id) {
    var opt = repo.findById(id);
    if (opt.isEmpty())return ResponseEntity.notFound().build();
    var adicional = opt.get();
    adicional.setActivo(false);
    repo.save(adicional);
    return ResponseEntity.noContent().build();
}

}