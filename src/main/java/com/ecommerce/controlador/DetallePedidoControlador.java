package com.ecommerce.controlador;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.modelo.DetallePedido;
import com.ecommerce.modelo.Pedido;
import com.ecommerce.servicio.DetallePedidoServicio;

@Controller
@RequestMapping("/detalle-pedido")
public class DetallePedidoControlador {
	@Autowired
    private DetallePedidoServicio detallePedidoServicio;

    @GetMapping("/listar/{pedidoId}")
    public String listarDetallePedido(@PathVariable Long pedidoId, Model modelo) {
        Pedido pedido = new Pedido();
        pedido.setId(pedidoId); // Asumiendo que ya tienes el ID del pedido
        List<DetallePedido> detalles = detallePedidoServicio.listarPorPedido(pedido);
        modelo.addAttribute("detalles", detalles);
        return "detalle_pedido";
    }

    @PostMapping("/guardar")
    public String guardarDetallePedido(@ModelAttribute("detallePedido") DetallePedido detallePedido) {
        detallePedidoServicio.guardarDetallePedido(detallePedido);
        return "redirect:/detalle-pedido/listar/" + detallePedido.getPedido().getId();
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarDetallePedido(@PathVariable Long id) {
        detallePedidoServicio.eliminarDetallePedido(id);
        return "redirect:/detalle-pedido/listar";
    }
}
