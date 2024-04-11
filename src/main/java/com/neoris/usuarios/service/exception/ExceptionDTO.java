package com.neoris.usuarios.service.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ExceptionDTO extends Exception {	
	
	private static final long serialVersionUID = 1L;
	private String code;
	private HttpStatus status;
	
	public  ExceptionDTO(String code, HttpStatus status, String message) {
		super(message);
		this.code=code;
		this.status=status;
	}
	
}
