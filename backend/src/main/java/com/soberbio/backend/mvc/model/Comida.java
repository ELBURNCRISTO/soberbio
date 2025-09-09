package com.soberbio.backend.mvc.model;

public class Comida {
    private Long id;
    private String nombre;
    private String descripcion;
    private int precio;      // COP
    private String imgUrl;   // URL (String)

    public Comida(Long id, String nombre, String descripcion, int precio, String imgUrl) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imgUrl = imgUrl;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public int getPrecio() { return precio; }
    public String getImgUrl() { return imgUrl; }
}