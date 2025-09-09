package com.soberbio.backend.web;

import com.soberbio.backend.domain.*;
import com.soberbio.backend.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
  private final ClienteRepo clientes;
  private final PedidoRepo pedidos;

  public ClienteController(ClienteRepo c, PedidoRepo p){ this.clientes=c; this.pedidos=p; }

  @GetMapping("/pedidos")
  public String activos(HttpSession s, Model model){
    Long id = (Long) s.getAttribute("CLIENTE_ID");
    if(id==null) return "redirect:/login";
    Cliente c = clientes.findById(id).orElseThrow();
    List<Pedido> activos = pedidos.findByClienteAndEstadoNot(c,EstadoPedido.ENTREGADO);
    model.addAttribute("pedidos", activos);
    return "cliente/pedidos";
  }

  @GetMapping("/historial")
  public String historial(HttpSession s, Model model){
    Long id = (Long) s.getAttribute("CLIENTE_ID");
    if(id==null) return "redirect:/login";
    Cliente c = clientes.findById(id).orElseThrow();
    model.addAttribute("pedidos", pedidos.findByClienteAndEstado(c, EstadoPedido.ENTREGADO));
    return "cliente/historial";
  }

  @GetMapping("/perfil")
  public String perfil(HttpSession s, Model m){
    Long id = (Long) s.getAttribute("CLIENTE_ID");
    if(id==null) return "redirect:/login";
    m.addAttribute("c", clientes.findById(id).orElseThrow());
    return "cliente/perfil";
  }
}
