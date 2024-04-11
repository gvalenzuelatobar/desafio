	package com.neoris.usuarios.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neoris.usuarios.entity.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
	
	public ArrayList<Usuario> findAll();
	
	Optional<Usuario> findByUsername(String username); 
}
