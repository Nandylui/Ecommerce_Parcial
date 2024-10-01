package com.ecommerce.controlador;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
/*import org.springframework.data.repository.query.Param;
*/import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.ecommerce.dto.ClienteDTO;
import com.ecommerce.modelo.Cliente;
import com.ecommerce.repositorio.ClienteRepositorio;

@Controller
@RequestMapping("/clientes")
public class ClienteControlador {
	
	@Autowired
	//private ClienteServicioImpl servicio;
	private ClienteRepositorio servicio;
	
	@GetMapping({"","/"})
	public String obtenerClientes(Model model) {
		var clientes=servicio.findAll(Sort.by(Sort.Direction.DESC,"id"));
		model.addAttribute("cliente", clientes);
		return "clientes/index";
	}
	
	@GetMapping("/crear")
	public String crearCliente(Model model) {
		ClienteDTO clienteDTO=new ClienteDTO();
		model.addAttribute("clienteDTO", clienteDTO);
		return "clientes/crear";
	}
	
	@PostMapping("/crear")
	public String createCliente(
			@Valid @ModelAttribute ClienteDTO clienteDTO, BindingResult result) {
		if(servicio.findByEmail(clienteDTO.getEmail())!=null) {
			result.addError(new FieldError("clienteDTO", "email", clienteDTO.getEmail(), false, null, null, "Email ya usuado"));
		}
	
		if(result.hasErrors()) {
			return "clientes/crear";
		}
		Cliente cliente= new Cliente();
		cliente.setNombre(clienteDTO.getNombre());
		cliente.setApellido(clienteDTO.getApellido());
		cliente.setDireccion(clienteDTO.getDireccion());
		cliente.setEmail(clienteDTO.getEmail());
		cliente.setTelefono(clienteDTO.getTelefono());
		
		servicio.save(cliente);
		
		return "redirect:/clientes";
	}
	
	@GetMapping("/editar/{id}")
	public String editarCliente(Model model, @PathVariable Long id) {
		Cliente cliente=servicio.findById(id).orElse(null);
		if(cliente==null) {
			return "redirect:/clientes";
		}
		
		ClienteDTO clienteDTO= new ClienteDTO();
		clienteDTO.setNombre(cliente.getNombre());
		clienteDTO.setApellido(cliente.getApellido());
		clienteDTO.setDireccion(cliente.getDireccion());
		clienteDTO.setEmail(cliente.getEmail());
		clienteDTO.setTelefono(cliente.getTelefono());
		
		model.addAttribute("clienteDTO", clienteDTO);
		model.addAttribute("cliente", cliente);
		
		
		return "clientes/editar";
	}
	
	@PostMapping("/editar/{id}")
	public String editarCliente(Model model, @PathVariable Long id, @Valid @ModelAttribute ClienteDTO clienteDTO, BindingResult result) {
		
		Cliente cliente=servicio.findById(id).orElse(null);
		if(cliente==null) {
			return "redirect:/clientes";
		}
		
		/*aqui se usa para el boton guardar th:action="@{/ruta}"*/
		model.addAttribute("cliente", cliente);
		
		if(result.hasErrors()) {
			return "clientes/editar";
		}
		
		cliente.setNombre(clienteDTO.getNombre());
		cliente.setApellido(clienteDTO.getApellido());
		cliente.setDireccion(clienteDTO.getDireccion());
		cliente.setEmail(clienteDTO.getEmail());
		cliente.setTelefono(clienteDTO.getTelefono());
		
		try {
			servicio.save(cliente);
		}catch(Exception ex){
			result.addError(new FieldError("clienteDTO", "email", clienteDTO.getEmail(), false, null, null, "El email ya esta usado"));
			model.addAttribute("cliente", cliente);//añadi esta fila
			return "clientes/editar";
		}
		return "redirect:/clientes";
	}
	
	@GetMapping("/eliminar")
	public String eliminarCliente(@RequestParam Long id) {
		Cliente cliente = servicio.findById(id).orElse(null);
		if(cliente != null) {
			servicio.delete(cliente);
		}
		return "redirect:/clientes";
	}
	
	
	
	
	
	
	
	
	
			
}
