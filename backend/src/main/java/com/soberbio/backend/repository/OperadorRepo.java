package com.soberbio.backend.repository;

import com.soberbio.backend.domain.Operador;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OperadorRepo extends JpaRepository<Operador,Long> {
  Optional<Operador> findByUsernameAndPassword(String username, String password);
}
