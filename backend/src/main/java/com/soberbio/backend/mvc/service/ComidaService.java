package com.soberbio.backend.mvc.service;

import com.soberbio.backend.mvc.model.Comida;
import com.soberbio.backend.mvc.repository.ComidaFakeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComidaService {

    private final ComidaFakeRepository repo;

    public ComidaService(ComidaFakeRepository repo) {
        this.repo = repo;
    }

    public List<Comida> listar() {
        return repo.findAll();
    }

    public Optional<Comida> buscar(Long id) {
        return repo.findById(id);
    }
}