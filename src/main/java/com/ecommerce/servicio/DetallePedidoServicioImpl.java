package com.ecommerce.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.modelo.DetallePedido;
import com.ecommerce.modelo.Pedido;
import com.ecommerce.repositorio.DetallePedidoRepositorio;

@Service
public class DetallePedidoServicioImpl implements DetallePedidoServicio{
	@Autowired
    private DetallePedidoRepositorio  detallePedidoRepositorio;
	
	//@Override
    public List<DetallePedido> listarPorPedido(Pedido pedido) {
        return detallePedidoRepositorio.findByPedido(pedido);
    }

    //@Override
    public DetallePedido guardarDetallePedido(DetallePedido detallePedido) {
        return detallePedidoRepositorio.save(detallePedido);
    }

    //@Override
    public void eliminarDetallePedido(Long id) {
        detallePedidoRepositorio.deleteById(id);
    }
	
}
