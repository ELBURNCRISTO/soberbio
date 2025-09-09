package com.soberbio.backend.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "domiciliario")
public class Domiciliario {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nombre;
  private String celular;

  @Column(unique = true)
  private String cedula;

  private boolean disponible = true;

  // getters/setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }
  public String getCelular() { return celular; }
  public void setCelular(String celular) { this.celular = celular; }
  public String getCedula() { return cedula; }
  public void setCedula(String cedula) { this.cedula = cedula; }
  public boolean isDisponible() { return disponible; }
  public void setDisponible(boolean disponible) { this.disponible = disponible; }
}