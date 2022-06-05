package com.hulkstore.todo1.security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hulkstore.todo1.security.entity.Usuario;
import com.hulkstore.todo1.security.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
		return usuarioRepository.findByNombreUsuario(nombreUsuario);
	}
	
	public boolean existByNombreUsuario(String nombreUsuario) {
		return usuarioRepository.existsByNombreUsuario(nombreUsuario);
	}
	public boolean existByEmail(String email) {
		return usuarioRepository.existsByEmail(email);
	}
	public void save (Usuario usuario) {
		usuarioRepository.save(usuario);
	}
}
