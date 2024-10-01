package com.ecommerce.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.modelo.Pedido;
import com.ecommerce.repositorio.PedidoRepositorio;

@Service
public class PedidoServicioImpl {
	@Autowired
    private PedidoRepositorio repositorio;
	
	@Transactional(readOnly=true)
    public List<Pedido> findAll(){
    	return (List<Pedido>) repositorio.findAll();
    }
	
    public List<Pedido> listarTodosLosPedidos(String palabraClave){
    	if(palabraClave!=null) {
    		return repositorio.findByEstado(palabraClave);
    	}
    	return repositorio.findAll();
    }
    
	//@Override
    public List<Pedido> listarTodosLosPedidos() {
        return repositorio.findAll();
    }

    //@Override
    public void guardarPedido(Pedido pedido) {
        repositorio.save(pedido);
    }

    //@Override
    public Pedido obtenerPedidoPorId(Long id) {
        return repositorio.findById(id).get();
    }
    
    public Pedido actualizarPedido(Pedido pedido) {
    	return repositorio.save(pedido);
    }

    //@Override
    public void eliminarPedido(Long id) {
        repositorio.deleteById(id);
    }
}
