package com.neoris.usuarios.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neoris.usuarios.entity.Usuario;
import com.neoris.usuarios.request.UsuarioRequest;
import com.neoris.usuarios.service.JwtService;
import com.neoris.usuarios.service.UsuarioService;
import com.neoris.usuarios.service.exception.ExceptionDTO;
import com.neoris.usuarios.util.JsonResponse;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	private final Logger log = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	@Operation(summary = "Obtiene todos los valores", description = "Responde todos los datos que se encuentran enla BD")	
	@GetMapping("/all")
    public ArrayList<Usuario>  all() {
		ArrayList<Usuario> response =usuarioService.findAll();
		return  response  ;
		
	}
	
	@Operation(summary = "Graba un registro en la BD", description = "un usuario y una lista de telefonos")	
	@PostMapping("/ingreso")
    public ResponseEntity<JsonResponse> ingreso( @RequestBody UsuarioRequest user) throws ExceptionDTO {
		JsonResponse jr = new JsonResponse();
		
		jr =usuarioService.create(user);		
        if (jr == null) {
             new ResponseEntity<>(jr, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
        	new ResponseEntity<>(jr, HttpStatus.OK); 
        }
	
	
    return new ResponseEntity<>( jr, HttpStatus.OK);
	}
	
}
	
	
