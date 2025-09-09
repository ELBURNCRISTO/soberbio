package com.soberbio.backend.domain;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "pedido_item")
public class PedidoItem {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false)
  private Pedido pedido;

  @ManyToOne(optional = false)
  private Producto producto;

  private int cantidad;

  @ManyToMany
  @JoinTable(name="pedido_item_adicional",
      joinColumns = @JoinColumn(name = "pedido_item_id"),
      inverseJoinColumns = @JoinColumn(name = "adicional_id"))
  private Set<Adicional> adicionales = new HashSet<>();

  private int precioUnitario;

  // getters y setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public Pedido getPedido() { return pedido; }
  public void setPedido(Pedido pedido) { this.pedido = pedido; }
  public Producto getProducto() { return producto; }
  public void setProducto(Producto producto) { this.producto = producto; }
  public int getCantidad() { return cantidad; }
  public void setCantidad(int cantidad) { this.cantidad = cantidad; }
  public Set<Adicional> getAdicionales() { return adicionales; }
  public void setAdicionales(Set<Adicional> adicionales) { this.adicionales = adicionales; }
  public int getPrecioUnitario() { return precioUnitario; }
  public void setPrecioUnitario(int precioUnitario) { this.precioUnitario = precioUnitario; }
}
