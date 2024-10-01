package com.ecommerce.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.modelo.DetallePedido;
import com.ecommerce.modelo.Pedido;

@Repository
public interface DetallePedidoRepositorio extends JpaRepository<DetallePedido, Long> {
	List<DetallePedido> findByPedido(Pedido pedido);
}
