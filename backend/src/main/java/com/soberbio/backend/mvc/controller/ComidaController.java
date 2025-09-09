package com.soberbio.backend.mvc.controller;
import com.soberbio.backend.mvc.service.ComidaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comidas")
public class ComidaController {

    private final ComidaService service;

    public ComidaController(ComidaService service) {
        this.service = service;
    }

    // Tabla
    @GetMapping
    public String tabla(Model model) {
        model.addAttribute("comidas", service.listar());
        return "comidas/tabla";  
    }

    // Tarjetas
    @GetMapping("/cards")
    public String cards(Model model) {
        model.addAttribute("comidas", service.listar());
        return "comidas/cards"; 
    }

    // Detalle
    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        var comida = service.buscar(id).orElse(null);
        model.addAttribute("comida", comida);
        return "comidas/detalle";
    }
}