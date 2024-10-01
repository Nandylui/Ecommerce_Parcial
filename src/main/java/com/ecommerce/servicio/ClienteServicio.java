package com.ecommerce.servicio;

import java.util.List;
import java.util.Optional;

import com.ecommerce.modelo.Cliente;

public interface ClienteServicio {
	List<Cliente>obtenerTodosLosClientes();
	Optional<Cliente>obtenerClientePorId(Long id);
	Cliente guardarCliente(Cliente cliente);
	void eliminarCliente(Long id);
}
