package com.neoris.usuarios.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDTO {
	
	private String code;
	private String mensaje;
}
