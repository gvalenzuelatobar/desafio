package com.neoris.usuarios.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@AllArgsConstructor
public class UsuarioRequest {
	
	private String name;
	private String email;
	private String password;
	public List<PhoneRequest> phones;

}
