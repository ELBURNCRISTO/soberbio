package com.soberbio.backend.util;

import com.soberbio.backend.domain.Adicional;
import com.soberbio.backend.domain.Producto;
import java.util.*;

public class Cart {
  public static class Item {
    public Producto producto;
    public int cantidad;
    public Set<Adicional> adicionales = new HashSet<>();
    public int subtotal() {
      int add = adicionales.stream().mapToInt(Adicional::getPrecio).sum();
      return cantidad * (producto.getPrecio() + add);
    }
  }
  private final Map<Long,Item> items = new LinkedHashMap<>();
  public Collection<Item> getItems(){ return items.values(); }
  public void add(Producto p, int cantidad, Set<Adicional> ads){
    Item it = items.getOrDefault(p.getId(), new Item());
    it.producto = p;
    it.cantidad += cantidad;
    it.adicionales.addAll(ads);
    items.put(p.getId(), it);
  }
  public int total(){ return items.values().stream().mapToInt(Item::subtotal).sum(); }
  public void clear(){ items.clear(); }
  public boolean isEmpty(){ return items.isEmpty(); }
}
