package com.soberbio.backend.web;

import com.soberbio.backend.domain.Producto;
import com.soberbio.backend.domain.Adicional;
import com.soberbio.backend.domain.*;
import com.soberbio.backend.repository.*;
import com.soberbio.backend.util.Cart;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("/carrito")
public class CarritoController {
  private final ProductoRepo productos;
  private final AdicionalRepo adicionales;
  private final ClienteRepo clientes;
  private final PedidoRepo pedidos;

  public CarritoController(ProductoRepo p, AdicionalRepo a, ClienteRepo c, PedidoRepo pe){
    this.productos=p; this.adicionales=a; this.clientes=c; this.pedidos=pe;
  }

  private Cart getCart(HttpSession s){
    Cart cart = (Cart) s.getAttribute("CART");
    if(cart==null){ cart = new Cart(); s.setAttribute("CART", cart); }
    return cart;
  }

  @PostMapping("/add")
  public String add(@RequestParam Long productoId,
                    @RequestParam(defaultValue="1") int cantidad,
                    @RequestParam(required=false,name="adicional") List<Long> adicionalesIds,
                    HttpSession session){
    Producto p = productos.findById(productoId).orElseThrow();
    Set<Adicional> ads = new HashSet<>();
    if(adicionalesIds!=null) ads.addAll(adicionales.findAllById(adicionalesIds));
    getCart(session).add(p,cantidad,ads);
    return "redirect:/carrito";
  }

  @GetMapping
  public String ver(HttpSession session, Model model){
    Cart cart = getCart(session);
    model.addAttribute("cart", cart);
    return "carrito";
  }

  @PostMapping("/confirmar")
  public String confirmar(HttpSession s){
    Long clienteId = (Long) s.getAttribute("CLIENTE_ID");
    if(clienteId==null) return "redirect:/login";
    Cart cart = getCart(s);
    if(cart.isEmpty()) return "redirect:/menu";

    Pedido pedido = new Pedido();
    pedido.setCliente(clientes.findById(clienteId).orElseThrow());
    pedido.setEstado(EstadoPedido.RECIBIDO);
    pedido.setFechaCreacion(LocalDateTime.now());

    for(Cart.Item it : cart.getItems()){
      PedidoItem pi = new PedidoItem();
      pi.setPedido(pedido);
      pi.setProducto(it.producto);
      pi.setCantidad(it.cantidad);
      pi.setPrecioUnitario(it.producto.getPrecio());
      pi.getAdicionales().addAll(it.adicionales);
      pedido.getItems().add(pi);
    }
    pedidos.save(pedido);
    cart.clear();
    return "redirect:/cliente/pedidos";
  }
}
