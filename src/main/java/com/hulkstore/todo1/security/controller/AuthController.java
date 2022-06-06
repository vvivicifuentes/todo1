package com.hulkstore.todo1.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.hulkstore.todo1.app.dto.Mensaje;
import com.hulkstore.todo1.security.dto.JwtDto;
import com.hulkstore.todo1.security.dto.LoginUsuario;
import com.hulkstore.todo1.security.dto.NuevoUsuario;
import com.hulkstore.todo1.security.entity.Rol;
import com.hulkstore.todo1.security.entity.Usuario;
import com.hulkstore.todo1.security.enums.RolNombre;
import com.hulkstore.todo1.security.jwt.JwtProvider;
import com.hulkstore.todo1.security.service.RolService;
import com.hulkstore.todo1.security.service.UsuarioService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	RolService rolService;
	
	@Autowired
	JwtProvider jwtProvider;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/nuevo")
	public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
		if (bindingResult.hasErrors())
			return new ResponseEntity<Object>(new Mensaje("Campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST );
		if(usuarioService.existByNombreUsuario(nuevoUsuario.getNombreUsuario()))
			return new ResponseEntity<Object>(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		if(usuarioService.existByEmail(nuevoUsuario.getEmail()))
			return new ResponseEntity<Object>(new Mensaje("Ese email ya existe"), HttpStatus.BAD_REQUEST);
		
		Usuario usuario =
				new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
						passwordEncoder.encode(nuevoUsuario.getPassword()));
		
		Set<Rol> roles = new HashSet<>();
		roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
		
		if(nuevoUsuario.getRoles().contains("admin"))
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
		usuario.setRoles(roles);
		usuarioService.save(usuario);
		return new ResponseEntity<Object>(new Mensaje("Usuario guardado"), HttpStatus.CREATED);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/login")
	public ResponseEntity<JwtDto> login (@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult ){
		if (bindingResult.hasErrors())
			return new ResponseEntity(new Mensaje("Campos mal puestos"), HttpStatus.BAD_REQUEST );
		Authentication authentication =
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generatedToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		
		return new ResponseEntity<JwtDto>(jwtDto, HttpStatus.OK);
	}
	
}
