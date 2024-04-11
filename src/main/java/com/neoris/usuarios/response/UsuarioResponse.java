package com.neoris.usuarios.response;

import java.util.Date;

import lombok.Data;


@Data
public class UsuarioResponse {
	
	private String id;
	private Date fechaCreada;
	private Date fechaModificada;
	private Date ultimoLogin;
	private String token;
	private Boolean activo;
	

}
