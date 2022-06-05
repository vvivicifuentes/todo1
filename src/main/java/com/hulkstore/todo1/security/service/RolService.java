package com.hulkstore.todo1.security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hulkstore.todo1.security.entity.Rol;
import com.hulkstore.todo1.security.enums.RolNombre;
import com.hulkstore.todo1.security.repository.RolRepository;

@Service
@Transactional
public class RolService {
	@Autowired
	RolRepository rolRepository;
	
	public Optional<Rol> getByRolNombre(RolNombre rolNombre){
		return rolRepository.findByRolNombre(rolNombre);
	}
}
