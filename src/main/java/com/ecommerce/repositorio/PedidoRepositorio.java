package com.ecommerce.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.modelo.Pedido;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Long>{
	//List<Pedido>findAll(String palabraClave);
	List<Pedido>findByEstado(String palabraClave);
	public List<Pedido>findAll();
}
