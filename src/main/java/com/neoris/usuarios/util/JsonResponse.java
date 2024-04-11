package com.neoris.usuarios.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonResponse {
	
	private String idUsuario;
	private String fechaCreacion;
	private String fechaModificacion;
	private String fechaIngreso;
	private String token;
	private Boolean activo;
}
