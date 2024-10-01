package com.ecommerce.modelo;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, name = "direccion", length = 128)
	private String direccion;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Column(name = "fechaEntrega")
	private LocalDate fechaEntrega;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Column(name = "fechaPedido")
	private LocalDate fechaPedido;

	@Column(nullable = false, name = "estado", length = 128)
	private String estado;

	@ManyToOne(cascade = CascadeType.PERSIST) // Relación con TipoPago
	@JoinColumn(name = "tipo_pago_id", referencedColumnName = "id")
	private TipoPago tipoPago;

	@ManyToOne(cascade = CascadeType.PERSIST) // Relación con Usuario
	@JoinColumn(name = "usuario_id", referencedColumnName = "id")
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public LocalDate getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(LocalDate fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public LocalDate getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(LocalDate fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public TipoPago getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(TipoPago tipoPago) {
		this.tipoPago = tipoPago;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Pedido(Long id, String direccion, LocalDate fechaEntrega, LocalDate fechaPedido, String estado,
			TipoPago tipoPago, Usuario usuario) {
		super();
		this.id = id;
		this.direccion = direccion;
		this.fechaEntrega = fechaEntrega;
		this.fechaPedido = fechaPedido;
		this.estado = estado;
		this.tipoPago = tipoPago;
		this.usuario = usuario;
	}

	public Pedido(String direccion, LocalDate fechaEntrega, LocalDate fechaPedido, String estado, TipoPago tipoPago,
			Usuario usuario) {
		super();
		this.direccion = direccion;
		this.fechaEntrega = fechaEntrega;
		this.fechaPedido = fechaPedido;
		this.estado = estado;
		this.tipoPago = tipoPago;
		this.usuario = usuario;
	}

	public Pedido() {
		super();
	}

}
