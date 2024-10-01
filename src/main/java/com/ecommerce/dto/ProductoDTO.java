package com.ecommerce.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class ProductoDTO {

	private Long id;
	private String nombre;
	private String marca;
	private String categoria;
	@Min(0)
	private double precio;

	@Size(min = 10, message = "The description should be at least 10 character")
	@Size(max = 2000, message = "The description cannot exceed 2000 character")
	private String descripcion;
	private String color;
	private MultipartFile imagenNombre;

	public ProductoDTO(Long id, String nombre, String marca, String categoria, @Min(0) double precio,
			@Size(min = 10, message = "The description should be at least 10 character") @Size(max = 2000, message = "The description cannot exceed 2000 character") String descripcion,
			String color, MultipartFile imagenNombre) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.marca = marca;
		this.categoria = categoria;
		this.precio = precio;
		this.descripcion = descripcion;
		this.color = color;
		this.imagenNombre = imagenNombre;
	}

	public ProductoDTO(String nombre, String marca, String categoria, @Min(0) double precio,
			@Size(min = 10, message = "The description should be at least 10 character") @Size(max = 2000, message = "The description cannot exceed 2000 character") String descripcion,
			String color, MultipartFile imagenNombre) {
		super();
		this.nombre = nombre;
		this.marca = marca;
		this.categoria = categoria;
		this.precio = precio;
		this.descripcion = descripcion;
		this.color = color;
		this.imagenNombre = imagenNombre;
	}

	public ProductoDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public MultipartFile getImagenNombre() {
		return imagenNombre;
	}

	public void setImagenNombre(MultipartFile imagenNombre) {
		this.imagenNombre = imagenNombre;
	}

}
