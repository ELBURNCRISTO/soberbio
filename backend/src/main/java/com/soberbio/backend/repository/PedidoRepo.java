package com.soberbio.backend.repository;

import com.soberbio.backend.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Collection;

public interface PedidoRepo extends JpaRepository<Pedido,Long> {
  List<Pedido> findByClienteAndEstadoNot(Cliente c, EstadoPedido estado);
  List<Pedido> findByClienteAndEstado(Cliente c, EstadoPedido estado);
  List<Pedido> findByEstadoIn(Collection<EstadoPedido> estados);
}
