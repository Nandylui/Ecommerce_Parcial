package com.ecommerce.servicio;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ecommerce.dto.UsuarioRegistroDTO;
import com.ecommerce.modelo.Usuario;

public interface UsuarioServicio extends UserDetailsService{
	
	public Usuario guardar(UsuarioRegistroDTO registroDTO);
	
	public List<Usuario> listarUsuarios();
	
	
}
