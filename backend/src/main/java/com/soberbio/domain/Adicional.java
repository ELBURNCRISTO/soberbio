package com.soberbio.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
public class Adicional {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank @Column(nullable=false, unique=true)
  private String nombre;

  @NotNull @DecimalMin("0.0")
  @Column(nullable=false, precision=10, scale=2)
  private BigDecimal precio;

  @Column(nullable=false)
  private boolean activo = true;

  // getters y setters
  public Long getId(){ return id; }
  public void setId(Long id){ this.id = id; }
  public String getNombre(){ return nombre; }
  public void setNombre(String nombre){ this.nombre = nombre; }
  public BigDecimal getPrecio(){ return precio; }
  public void setPrecio(BigDecimal precio){ this.precio = precio; }
  public boolean isActivo(){ return activo; }
  public void setActivo(boolean activo){ this.activo = activo; }
}