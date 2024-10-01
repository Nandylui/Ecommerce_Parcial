package com.ecommerce.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "marca")
	private String marca;
	@Column(name = "categoria")
	private String categoria;
	@Column(name = "precio")
	private double precio;

	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "color")
	private String color;
	@Column(name = "imagen")
	private String imagenNombre;

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

	public String getImagenNombre() {
		return imagenNombre;
	}

	public void setImagenNombre(String imagenNombre) {
		this.imagenNombre = imagenNombre;
	}

	public Producto(Long id, String nombre, String marca, String categoria, double precio, String descripcion,
			String color, String imagenNombre) {
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

	public Producto(String nombre, String marca, String categoria, double precio, String descripcion, String color,
			String imagenNombre) {
		super();
		this.nombre = nombre;
		this.marca = marca;
		this.categoria = categoria;
		this.precio = precio;
		this.descripcion = descripcion;
		this.color = color;
		this.imagenNombre = imagenNombre;
	}

	public Producto() {
		super();
	}

}
