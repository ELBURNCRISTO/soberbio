package com.soberbio.backend.web;

import com.soberbio.backend.domain.Producto;
import com.soberbio.backend.domain.Adicional;
import com.soberbio.backend.repository.ProductoRepo;
import com.soberbio.backend.repository.AdicionalRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductoController {
  private final ProductoRepo productos;
  private final AdicionalRepo adicionales;
  public ProductoController(ProductoRepo p, AdicionalRepo a){ this.productos=p; this.adicionales=a; }

  @GetMapping("/menu")
  public String menu(Model model){
    model.addAttribute("productos", productos.findByActivoTrue());
    return "productos";
  }

  @GetMapping("/producto/{id}")
  public String detalle(@PathVariable Long id, Model model){
    Producto p = productos.findById(id).orElse(null);
    model.addAttribute("p", p);
    model.addAttribute("adicionales", adicionales.findAll());
    return "producto-detalle";
  }
}
