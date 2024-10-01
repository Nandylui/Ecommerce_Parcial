package com.ecommerce.servicio;

import java.util.List;

import com.ecommerce.modelo.DetallePedido;
import com.ecommerce.modelo.Pedido;

public interface DetallePedidoServicio {
	List<DetallePedido> listarPorPedido(Pedido pedido);
    DetallePedido guardarDetallePedido(DetallePedido detallePedido);
    void eliminarDetallePedido(Long id);
}
