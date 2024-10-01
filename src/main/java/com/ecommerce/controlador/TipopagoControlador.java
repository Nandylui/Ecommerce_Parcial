package com.ecommerce.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ecommerce.modelo.TipoPago;
import com.ecommerce.servicio.TipoPagoServicioImpl;

@Controller
public class TipopagoControlador {

	@Autowired
    private TipoPagoServicioImpl servicio;

    @GetMapping("/tipopagos")
    public String listarTipoPagos(Model modelo) {
        List<TipoPago> listaTipoPagos = servicio.listarTodosLosTiposPago();
        modelo.addAttribute("listaTipoPagos", listaTipoPagos);
        return "tipopagos";
    }

    @GetMapping("/tipopagos/nuevo")
    public String mostrarFormularioNuevoTipoPago(Model modelo) {
        TipoPago tipoPago = new TipoPago();
        modelo.addAttribute("tipoPago", tipoPago);
        return "nuevo_tipopago";
    }

    @PostMapping("/tipopagos")
    public String guardarTipoPago(TipoPago tipoPago) {
        servicio.guardarTipoPago(tipoPago);
        return "redirect:/tipopagos";
    }
    
    @GetMapping("/tipopagos/{id}")
    public String obtenerTipoPago(@PathVariable Long id, Model modelo) {
    	TipoPago tipopago=servicio.obtenerTipoPagoPorId(id);
    	modelo.addAttribute("tipopago", tipopago);
    	return "detalle_tipopago";
    }
    
    @GetMapping("/tipopagos/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model modelo) {
    	modelo.addAttribute("tipopago", servicio.obtenerTipoPagoPorId(id));
    	return "editar_tipopago";
    }
    
    @PostMapping("/tipopagos/actualizar/{id}")
    public String actualizarTipoPago(@PathVariable Long id, @ModelAttribute("tipopago") TipoPago tipopago, Model modelo) {
    	TipoPago TipoPagoExistente=servicio.obtenerTipoPagoPorId(id);
    	TipoPagoExistente.setId(id);
    	TipoPagoExistente.setNombre(tipopago.getNombre());
    	servicio.actualizarTipoPago(TipoPagoExistente);
    	return "redict:/tipopagos";
    }
    
    
    
}
