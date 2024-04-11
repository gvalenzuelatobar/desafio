package com.neoris.usuarios.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.neoris.usuarios.controller.dto.ErrorDTO;
import com.neoris.usuarios.service.exception.RequestException;

@RestControllerAdvice
public class ControllerAdvice {
	
	@ExceptionHandler(value = RequestException.class)
	public ResponseEntity<ErrorDTO> requestExceptionHandler(RequestException ex){
	
		ErrorDTO error = ErrorDTO.builder().code(ex.getCode()).mensaje(ex.getMessage()).build();
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		
		
		 
	}

}
