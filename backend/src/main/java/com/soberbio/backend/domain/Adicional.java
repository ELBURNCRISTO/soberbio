package com.soberbio.backend.domain;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "adicional")
public class Adicional {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nombre;

  // Para Sprint 1 simplificamos a entero en COP (compatible con tu data.sql)
  private int precio;

  private boolean activo = true;

  @ManyToMany(mappedBy = "adicionales")
  private Set<Producto> productos = new HashSet<>();

  // getters/setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }
  public int getPrecio() { return precio; }
  public void setPrecio(int precio) { this.precio = precio; }
  public boolean isActivo() { return activo; }
  public void setActivo(boolean activo) { this.activo = activo; }
  public Set<Producto> getProductos() { return productos; }
  public void setProductos(Set<Producto> productos) { this.productos = productos; }
}