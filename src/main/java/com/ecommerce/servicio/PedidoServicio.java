package com.ecommerce.servicio;
import java.util.List;
import com.ecommerce.modelo.Pedido;


public interface PedidoServicio {
	public List<Pedido> listarTodosLosPedidos();
    void guardarPedido(Pedido pedido);
    Pedido obtenerPedidoPorId(Long id); 
    void eliminarPedido(Long id);
}
