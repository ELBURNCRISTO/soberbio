package com.soberbio.repository;

import com.soberbio.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> { }