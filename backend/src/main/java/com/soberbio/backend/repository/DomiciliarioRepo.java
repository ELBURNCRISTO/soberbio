package com.soberbio.backend.repository;

import com.soberbio.backend.domain.Domiciliario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DomiciliarioRepo extends JpaRepository<Domiciliario,Long> {
  List<Domiciliario> findByDisponibleTrue();
}
