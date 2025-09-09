package com.soberbio.backend.repository;

import com.soberbio.backend.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoRepo extends JpaRepository<Producto,Long> {
  List<Producto> findByActivoTrue();
}
