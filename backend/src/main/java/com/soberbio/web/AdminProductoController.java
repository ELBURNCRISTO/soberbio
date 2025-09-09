package com.soberbio.web;

import com.soberbio.domain.Producto;
import com.soberbio.repository.ProductoRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/productos")
@CrossOrigin
public class AdminProductoController {

    private final ProductoRepository repo;

    public AdminProductoController(ProductoRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Producto> list() {
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<Producto> create(@Valid @RequestBody Producto p) {
        return ResponseEntity.ok(repo.save(p));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> update(@PathVariable Long id, @Valid @RequestBody Producto p) {
        return repo.findById(id)
                .map(old -> {
                    p.setId(id);
                    return ResponseEntity.ok(repo.save(p));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

  @DeleteMapping("/{id}")
public ResponseEntity<Void> deactivate(@PathVariable Long id) {
    var opt = repo.findById(id);
    if (opt.isEmpty())return ResponseEntity.notFound().build();
    var p = opt.get();
    p.setActivo(false);
    repo.save(p);
    return ResponseEntity.noContent().build();
}
}