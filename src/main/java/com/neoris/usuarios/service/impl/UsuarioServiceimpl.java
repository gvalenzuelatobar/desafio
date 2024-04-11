package com.neoris.usuarios.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.neoris.usuarios.entity.Phone;
import com.neoris.usuarios.entity.Usuario;
import com.neoris.usuarios.jwt.JwtService;
import com.neoris.usuarios.repository.UsuarioRepositorio;
import com.neoris.usuarios.request.PhoneRequest;
import com.neoris.usuarios.request.UsuarioRequest;
import com.neoris.usuarios.service.UsuarioService;
import com.neoris.usuarios.service.exception.ExceptionDTO;
import com.neoris.usuarios.service.exception.RequestException;
import com.neoris.usuarios.util.FormatFecha;
import com.neoris.usuarios.util.JsonResponse;

@Service
public class UsuarioServiceimpl implements UsuarioService {
	
	private final Logger log = LoggerFactory.getLogger(UsuarioServiceimpl.class);
	
	@Autowired
	UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	JwtService jwtService;
	
	@Override
	public ArrayList<Usuario> findAll() {
		
		return usuarioRepositorio.findAll();
	}
	
	public JsonResponse create(UsuarioRequest usuarioRequest) throws ExceptionDTO {
		JsonResponse jr =new JsonResponse();
		log.info("usuarioRepositorio " + usuarioRequest);
		try {
		Boolean existeCorreo = validarCorreo(usuarioRequest.getEmail());
		if(existeCorreo) {
			throw new RequestException("EmaiError", "El Email YA EXISTE");
		}	
		log.info("existeCorreo " + existeCorreo);
		Usuario userResp = grabarUsuario(usuarioRequest);	
		jr.setActivo(true);
		jr.setIdUsuario(userResp.getUsername());
		jr.setFechaCreacion(FormatFecha.convertirfechaToString(userResp.getFechaCreacion()));
		jr.setFechaIngreso(FormatFecha.convertirfechaToString(userResp.getFechaIngreso()));
		jr.setFechaModificacion(FormatFecha.convertirfechaToString(userResp.getFechaModificacion()));
		jr.setActivo(true);
		jr.setToken("Token");
        } catch (DataAccessException ex) {
            System.out.println(ex.getCause().getMessage());
        }
		
		
		return jr;
		
	}

	public Usuario grabarUsuario(UsuarioRequest usuarioRequest) {
		
		String token = jwtService.getToken(usuarioRequest.getName());
		Usuario user = Usuario.builder()
				.username(usuarioRequest.getName())
				.email(usuarioRequest.getEmail())
				.password(usuarioRequest.getPassword())
				.phone(mapearPhones(usuarioRequest.getPhones()))
				.fechaCreacion(new Date())
				.fechaIngreso(new Date())
				.fechaModificacion(new Date())
				.token(token)
				.build();
		
		return usuarioRepositorio.save(user);
	}
	
	private List<Phone> mapearPhones(List<PhoneRequest> phones){
		
		List<Phone> lista = new ArrayList<>();
		for(PhoneRequest ph : phones)
		{	Phone phone = new Phone();
			phone.setNumber(ph.getNumber());
			phone.setCitycode(ph.getCitycode());
			phone.setContrycode(ph.getContrycode());
			lista.add(phone);
		}
		return lista;
	}
	
	
	private Boolean validarCorreo(String correo) {
		
		ArrayList<Usuario> listaUser= usuarioRepositorio.findAll();
		Usuario search = listaUser.stream()
	                .filter(user -> user.getEmail().equals(correo))
	                .limit(1)
	                .findFirst().orElse(null);
		return search == null ? false : true;
		
	}
}
