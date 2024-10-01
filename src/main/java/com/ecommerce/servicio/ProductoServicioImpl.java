package com.ecommerce.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.modelo.Producto;
import com.ecommerce.repositorio.ProductoRepositorio;

@Service
public class ProductoServicioImpl {
	@Autowired
	private ProductoRepositorio productoRepositorio;
	
	@Transactional(readOnly=true)
	public List<Producto>findAll(){
		return (List<Producto>) productoRepositorio.findAll();
	}
	
	public List<Producto>listarTodosLosProductos(String palabraClave){
		if(palabraClave!=null) {
			return productoRepositorio.findByNombre(palabraClave);
		}
		return productoRepositorio.findAll();
	}
	
	public List<Producto> listarTodosProductos(){
		return productoRepositorio.findAll();
	}
	
	public Producto guardarProducto(Producto producto) {
		return productoRepositorio.save(producto);
	}
	
	public Producto obtenerProductoId(Long id) {
		return productoRepositorio.findById(id).get();
	}
	
	public Producto actualizarProducto(Producto producto) {
		return productoRepositorio.save(producto);
	}
	
	public void eliminarProducto(Long id) {
		productoRepositorio.deleteById(id);
	}
	
	
}
