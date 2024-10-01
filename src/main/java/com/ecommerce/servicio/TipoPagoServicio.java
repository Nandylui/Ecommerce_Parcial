package com.ecommerce.servicio;

import java.util.List;

import com.ecommerce.modelo.TipoPago;

public interface TipoPagoServicio {
	List<TipoPago>listarTodosLosTiposDePago();
	TipoPago guardarTipoPago(TipoPago tipopago);
	TipoPago obtenerTipoPagoPorId(Long id);
	void eliminarTipoPago(Long id);
}
