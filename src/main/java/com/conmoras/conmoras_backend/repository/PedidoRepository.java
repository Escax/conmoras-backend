package com.conmoras.conmoras_backend.repository;

import com.conmoras.conmoras_backend.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long> {
    List<Pedido> findByEstado(Pedido.EstadoPedido estado);
}
