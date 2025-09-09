package com.soberbio.repository;

import com.soberbio.domain.Operador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OperadorRepository extends JpaRepository<Operador, Long> {
  List<Operador> findByActivoTrue();
  Optional<Operador> findByUsuario(String usuario);
  boolean existsByUsuario(String usuario);
}