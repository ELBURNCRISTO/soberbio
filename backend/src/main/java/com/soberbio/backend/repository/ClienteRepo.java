package com.soberbio.backend.repository;

import com.soberbio.backend.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClienteRepo extends JpaRepository<Cliente,Long> {
  Optional<Cliente> findByEmailAndPassword(String email, String password);
  Optional<Cliente> findByEmail(String email);
}
