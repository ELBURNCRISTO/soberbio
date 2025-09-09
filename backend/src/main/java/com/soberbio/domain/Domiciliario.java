package com.soberbio.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Domiciliario {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank @Column(nullable = false)
  private String nombre;

  @NotBlank @Column(nullable = false, unique = true, length = 40)
  private String cedula;

  @NotBlank @Column(nullable = false, length = 30)
  private String celular;

  @Column(nullable = false)
  private boolean disponible = true;

  @Column(nullable = false)
  private boolean activo = true;

  // getters y setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }
  public String getCedula() { return cedula; }
  public void setCedula(String cedula) { this.cedula = cedula; }
  public String getCelular() { return celular; }
  public void setCelular(String celular) { this.celular = celular; }
  public boolean isDisponible() { return disponible; }
  public void setDisponible(boolean disponible) { this.disponible = disponible; }
  public boolean isActivo() { return activo; }
  public void setActivo(boolean activo) { this.activo = activo; }
}