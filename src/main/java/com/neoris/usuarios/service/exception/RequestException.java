package com.neoris.usuarios.service.exception;

import lombok.Data;

@Data
public class RequestException extends RuntimeException{
	
	
	private static final long serialVersionUID = 1L;
	private String code;
	public RequestException(String code, String message) {
		super(message);
		this.code=code;
	}
}
