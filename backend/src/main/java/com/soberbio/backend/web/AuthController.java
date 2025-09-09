package com.soberbio.backend.web;

import com.soberbio.backend.domain.Operador;
import com.soberbio.backend.domain.Cliente;
import com.soberbio.backend.repository.ClienteRepo;
import com.soberbio.backend.repository.OperadorRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AuthController {
  private final ClienteRepo clientes;
  private final OperadorRepo operadores;

  public AuthController(ClienteRepo c, OperadorRepo o){ this.clientes=c; this.operadores=o; }

  // Cliente
  @GetMapping("/login") public String login(){ return "login"; }

  @PostMapping("/login")
  public String doLogin(@RequestParam String email, @RequestParam String password,
                        HttpSession session, Model model){
    Optional<Cliente> c = clientes.findByEmailAndPassword(email,password);
    if(c.isEmpty()){ model.addAttribute("error","Credenciales inválidas"); return "login"; }
    session.setAttribute("CLIENTE_ID", c.get().getId());
    return "redirect:/cliente/pedidos";
  }

  @GetMapping("/signup") public String signup(){ return "signup"; }

  @PostMapping("/signup")
  public String doSignup(@RequestParam String nombre, @RequestParam String apellido,
      @RequestParam String email, @RequestParam String password,
      @RequestParam(required=false) String telefono,
      @RequestParam(required=false) String direccion,
      HttpSession session, Model model){
    if(clientes.findByEmail(email).isPresent()){
      model.addAttribute("error","El correo ya está registrado"); return "signup";
    }
    Cliente c = new Cliente();
    c.setNombre(nombre); c.setApellido(apellido);
    c.setEmail(email); c.setPassword(password);
    c.setTelefono(telefono); c.setDireccion(direccion);
    clientes.save(c);
    session.setAttribute("CLIENTE_ID", c.getId());
    return "redirect:/cliente/pedidos";
  }

  @GetMapping("/logout")
  public String logout(HttpSession s){ s.invalidate(); return "redirect:/"; }

  // Operador
  @GetMapping("/operador/login") public String loginOp(){ return "operador/login"; }

  @PostMapping("/operador/login")
  public String doLoginOp(@RequestParam String username, @RequestParam String password,
                          HttpSession session, Model model){
    Optional<Operador> op = operadores.findByUsernameAndPassword(username,password);
    if(op.isEmpty()){ model.addAttribute("error","Credenciales inválidas"); return "operador/login"; }
    session.setAttribute("OPERADOR_ID", op.get().getId());
    return "redirect:/operador/pedidos";
  }
}
