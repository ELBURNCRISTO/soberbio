package com.soberbio.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Operador {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank @Column(nullable = false)
  private String nombre;

  @NotBlank @Column(nullable = false, unique = true, length = 64)
  private String usuario;

  @NotBlank @Column(nullable = false, length = 120)
  private String passwordHash;

  @Column(nullable = false)
  private boolean activo = true;

  // getters y setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }
  public String getUsuario() { return usuario; }
  public void setUsuario(String usuario) { this.usuario = usuario; }
  public String getPasswordHash() { return passwordHash; }
  public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
  public boolean isActivo() { return activo; }
  public void setActivo(boolean activo) { this.activo = activo; }
}
