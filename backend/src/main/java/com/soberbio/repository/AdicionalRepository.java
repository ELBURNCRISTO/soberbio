package com.soberbio.repository;

import com.soberbio.domain.Adicional;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AdicionalRepository extends JpaRepository<Adicional, Long> {
  List<Adicional> findByActivoTrue();
}