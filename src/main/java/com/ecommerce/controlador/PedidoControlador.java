package com.ecommerce.controlador;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ecommerce.modelo.Pedido;
import com.ecommerce.servicio.PedidoServicioImpl;

@Controller
public class PedidoControlador {
	@Autowired
    private PedidoServicioImpl servicio;

    @GetMapping("/pedidos")
    public String listarPedidos(Model modelo) {
        List<Pedido> listaPedidos = servicio.listarTodosLosPedidos();
        modelo.addAttribute("listaPedidos", listaPedidos);
        return "pedidos"; // Vista para listar pedidos
    }
    
    /*
    @GetMapping("/pedidos")
    public String listarPedidos(Model modelo, @Param("palarbaClave") String palabraClave) {
    	modelo.addAttribute("palabraClave", servicio.listarTodosLosPedidos(palabraClave));
    	modelo.addAttribute("palabraClave", palabraClave);
    	return "pedidos";
    }
    */
    

    @GetMapping("/pedidos/nuevo")
    public String mostrarFormularioNuevoPedido(Model modelo) {
        Pedido pedido = new Pedido();
        modelo.addAttribute("pedido", pedido);
        return "nuevo_pedido"; // Vista para crear nuevo pedido
    }

    @PostMapping("/pedidos")
    public String guardarPedido(@ModelAttribute Pedido pedido) {
        servicio.guardarPedido(pedido);
        return "redirect:/pedidos"; // Redirige a la lista de pedidos
    }
    
    /*
    @PostMapping("/pedidos")
    public String guardarCliente(@ModelAttribute("pedidos")Pedido pedido) {
    	servicio.guardarPedido(pedido);
    	return "redirect:/pedidos";
    }
    */

    @GetMapping("/pedidos/{id}")
    public String obtenerPedido(@PathVariable Long id, Model modelo) {
        Pedido pedido = servicio.obtenerPedidoPorId(id);
        modelo.addAttribute("pedido", pedido);
        return "detalle_pedido"; // Vista para mostrar detalles del pedido
    }
    
    @GetMapping("/pedidos/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model modelo) {
    	modelo.addAttribute("pedido", servicio.obtenerPedidoPorId(id));
    	//modelo.addAttribute("pedido", pedoido);
    	return "editar_pedido";
    }
    
    @PostMapping("/pedidos/actualizar/{id}")
    public String actualizarPedido(@PathVariable Long id, @ModelAttribute("pedido") Pedido pedido, Model modelo) {
    	Pedido PedidoExistente=servicio.obtenerPedidoPorId(id);
    	PedidoExistente.setId(id);
    	PedidoExistente.setDireccion(pedido.getDireccion());
    	PedidoExistente.setFechaEntrega(pedido.getFechaEntrega());
    	PedidoExistente.setFechaPedido(pedido.getFechaPedido());
    	PedidoExistente.setEstado(pedido.getEstado());
    	PedidoExistente.setTipoPago(pedido.getTipoPago());
    	PedidoExistente.setUsuario(pedido.getUsuario());
    	
    	servicio.actualizarPedido(PedidoExistente);
    	return "redirect:/pedidos";
    }
    
    @GetMapping("/pedidos/eliminar/{id}")
    public String eliminarPedido(@PathVariable Long id) {
        servicio.eliminarPedido(id);
        return "redirect:/pedidos"; // Redirige a la lista de pedidos
    }
}
