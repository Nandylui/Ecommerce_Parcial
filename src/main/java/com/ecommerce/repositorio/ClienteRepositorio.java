package com.ecommerce.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.modelo.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
	//List<Cliente>findAll(String palabraClave);
	List<Cliente>findByNombre(String palabraClave);
	public List<Cliente>findAll();
	public Cliente findByEmail(String email);
}
