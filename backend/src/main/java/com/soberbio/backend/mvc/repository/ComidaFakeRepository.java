package com.soberbio.backend.mvc.repository;

import com.soberbio.backend.mvc.model.Comida;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ComidaFakeRepository {

    private final List<Comida> data = new ArrayList<>(List.of(
        new Comida(1L, "Hamburguesa Clásica", "Pan artesanal, carne 150g", 18000, "https://picsum.photos/id/1062/600/400"),
        new Comida(2L, "Perro Americano", "Salchicha y salsas", 12000, "https://picsum.photos/id/1080/600/400"),
        new Comida(3L, "Salchipapas", "Papas + salchicha", 15000, "https://picsum.photos/id/1035/600/400")
    ));

    public List<Comida> findAll() { return data; }

    public Optional<Comida> findById(Long id) {
        return data.stream().filter(c -> Objects.equals(c.getId(), id)).findFirst();
    }
}