package com.ecommerce.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ecommerce.modelo.Producto;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long>{
	List<Producto>findByNombre(String palabraClave);
	public List<Producto>findAll();
}
