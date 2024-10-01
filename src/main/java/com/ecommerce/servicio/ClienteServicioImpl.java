package com.ecommerce.servicio;

import com.ecommerce.repositorio.ClienteRepositorio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.modelo.Cliente;

@Service
public class ClienteServicioImpl {
	@Autowired
	private ClienteRepositorio clienteRepositorio;
	
	@Transactional(readOnly=true)
	public List<Cliente> findAll(){
		return (List<Cliente>) clienteRepositorio.findAll();
	}
	
	public List<Cliente> listarTodosLosClientes(String palabraClave){
		if(palabraClave!=null) {
			return clienteRepositorio.findByNombre(palabraClave);
		}
		return clienteRepositorio.findAll();
	}
	
	public List<Cliente> listarTodosClientes(){
		return clienteRepositorio.findAll();
	}
	
	public Cliente guardarCliente(Cliente cliente) {
		return clienteRepositorio.save(cliente);
	}
	
	public Cliente obtenerClientePorId(Long id) {
		return clienteRepositorio.findById(id).get();
	}
	
	public Cliente actualizarCliente(Cliente cliente) {
		return clienteRepositorio.save(cliente);
	}
	
	public void eliminarCliente(Long id) {
		clienteRepositorio.deleteById(id);
	}
	
	
}
