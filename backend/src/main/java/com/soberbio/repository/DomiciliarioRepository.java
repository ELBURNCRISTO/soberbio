package com.soberbio.repository;

import com.soberbio.domain.Domiciliario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DomiciliarioRepository extends JpaRepository<Domiciliario, Long> {
  List<Domiciliario> findByActivoTrue();
  List<Domiciliario> findByActivoTrueAndDisponibleTrue();
  Optional<Domiciliario> findByCedula(String cedula);
  boolean existsByCedula(String cedula);
}