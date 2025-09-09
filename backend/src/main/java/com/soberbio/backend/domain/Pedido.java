package com.soberbio.backend.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "pedido")
public class Pedido {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false)
  private Cliente cliente;

  @Enumerated(EnumType.STRING)
  private EstadoPedido estado = EstadoPedido.RECIBIDO;

  private LocalDateTime fechaCreacion;
  private LocalDateTime fechaEntrega;

  @ManyToOne
  private Domiciliario domiciliario; // se asigna al pasar a ENVIADO

  @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<PedidoItem> items = new ArrayList<>();

  // getters y setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public Cliente getCliente() { return cliente; }
  public void setCliente(Cliente cliente) { this.cliente = cliente; }
  public EstadoPedido getEstado() { return estado; }
  public void setEstado(EstadoPedido estado) { this.estado = estado; }
  public LocalDateTime getFechaCreacion() { return fechaCreacion; }
  public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
  public LocalDateTime getFechaEntrega() { return fechaEntrega; }
  public void setFechaEntrega(LocalDateTime fechaEntrega) { this.fechaEntrega = fechaEntrega; }
  public Domiciliario getDomiciliario() { return domiciliario; }
  public void setDomiciliario(Domiciliario domiciliario) { this.domiciliario = domiciliario; }
  public List<PedidoItem> getItems() { return items; }
}
