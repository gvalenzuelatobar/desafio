package com.neoris.usuarios.service;

import java.util.ArrayList;

import com.neoris.usuarios.entity.Usuario;
import com.neoris.usuarios.request.UsuarioRequest;
import com.neoris.usuarios.service.exception.ExceptionDTO;
import com.neoris.usuarios.util.JsonResponse;


public interface UsuarioService{
	
	public ArrayList<Usuario> findAll();
	
	public JsonResponse create(UsuarioRequest usuarioRequest) throws ExceptionDTO;
	
	public Usuario grabarUsuario(UsuarioRequest usuarioRequest);

}


