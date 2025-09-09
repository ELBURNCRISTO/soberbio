package com.soberbio.backend.domain;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "producto")
public class Producto {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nombre;

  @Column(length = 1000)
  private String descripcion;

  private int precio;        // entero (COP)
  private String imgUrl;
  private boolean activo = true;

  @ManyToMany
  @JoinTable(name = "producto_adicional",
      joinColumns = @JoinColumn(name = "producto_id"),
      inverseJoinColumns = @JoinColumn(name = "adicional_id"))
  private Set<Adicional> adicionales = new HashSet<>();

  // getters/setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }
  public String getDescripcion() { return descripcion; }
  public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
  public int getPrecio() { return precio; }
  public void setPrecio(int precio) { this.precio = precio; }
  public String getImgUrl() { return imgUrl; }
  public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl; }
  public boolean isActivo() { return activo; }
  public void setActivo(boolean activo) { this.activo = activo; }
  public Set<Adicional> getAdicionales() { return adicionales; }
  public void setAdicionales(Set<Adicional> adicionales) { this.adicionales = adicionales; }
}