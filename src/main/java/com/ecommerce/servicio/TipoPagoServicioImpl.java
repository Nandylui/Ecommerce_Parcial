package com.ecommerce.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.modelo.TipoPago;
import com.ecommerce.repositorio.TipoPagoRepositorio;

@Service
public class TipoPagoServicioImpl{
	@Autowired
    private TipoPagoRepositorio repositorio; // Asegúrate de que esta interfaz esté creada

    
	@Transactional(readOnly=true)
	public List<TipoPago> findAll(){
		return (List<TipoPago>) repositorio.findAll();
	}
	
    public List<TipoPago> listarTodosLosTiposPago() {
        return repositorio.findAll(); // Devuelve todos los tipos de pago
    }

    
    public void guardarTipoPago(TipoPago tipoPago) {
        repositorio.save(tipoPago); // Guarda el tipo de pago
    }
    
    public TipoPago obtenerTipoPagoPorId(Long id) {
    	return repositorio.findById(id).get();
    }
    
    public TipoPago actualizarTipoPago(TipoPago tipopago) {
    	return repositorio.save(tipopago);
    }
    
    public void eliminarTipoPago(Long id) {
    	repositorio.deleteById(id);
    }
    
    
}
