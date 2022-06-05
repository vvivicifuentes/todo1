package com.hulkstore.todo1.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hulkstore.todo1.security.entity.Rol;
import com.hulkstore.todo1.security.enums.RolNombre;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
	Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
