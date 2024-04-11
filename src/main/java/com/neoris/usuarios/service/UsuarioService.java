package com.neoris.usuarios.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.neoris.usuarios.entity.Phone;
import com.neoris.usuarios.entity.Usuario;
import com.neoris.usuarios.repository.UsuarioRepositorio;
import com.neoris.usuarios.request.PhoneRequest;
import com.neoris.usuarios.request.UsuarioRequest;
import com.neoris.usuarios.service.exception.ExceptionDTO;
import com.neoris.usuarios.service.exception.RequestException;
import com.neoris.usuarios.util.FormatFecha;
import com.neoris.usuarios.util.JsonResponse;

@Service
public class UsuarioService{
	
private final Logger log = LoggerFactory.getLogger(UsuarioService.class);
	
	@Autowired
	UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	JwtService jwtService;
	
	public ArrayList<Usuario> findAll() {
		
		return usuarioRepositorio.findAll();
	}
	
	public JsonResponse create(UsuarioRequest usuarioRequest) throws ExceptionDTO {
		JsonResponse jr =new JsonResponse();
		
		try {
			
			if(!validarFormatoCorreo(usuarioRequest.getEmail())) {
				throw new RequestException("EmaiError", "El Email esta mal formateado");
			}
			
			if(validarCorreo(usuarioRequest.getEmail())) {
				throw new RequestException("EmaiError", "El Email Ya existe");
			}
			
			
			Usuario userResp = grabarUsuario(usuarioRequest);	
			jr.setActivo(true);
			jr.setIdUsuario(userResp.getUsername());
			jr.setFechaCreacion(FormatFecha.convertirfechaToString(userResp.getFechaCreacion()));
			jr.setFechaIngreso(FormatFecha.convertirfechaToString(userResp.getFechaIngreso()));
			jr.setFechaModificacion(FormatFecha.convertirfechaToString(userResp.getFechaModificacion()));
			jr.setActivo(true);
			jr.setToken(userResp.getToken());
        } catch (DataAccessException ex) {
            System.out.println(ex.getCause().getMessage());
        }
		
		
		return jr;
		
	}

	public Usuario grabarUsuario(UsuarioRequest usuarioRequest) {
		
		Usuario user = Usuario.builder()
				.idUsuario(UUID.randomUUID())
				.username(usuarioRequest.getName())
				.email(usuarioRequest.getEmail())
				.password(usuarioRequest.getPassword())
				.phone(mapearPhones(usuarioRequest.getPhones()))
				.fechaCreacion(new Date())
				.fechaIngreso(new Date())
				.fechaModificacion(new Date())
				.activo(true)
				.token(jwtService.getToken(usuarioRequest.getName()))
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
	
	
		public static Boolean validarFormatoCorreo (String email) {
			Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
			Matcher matcher = pattern.matcher(email);
			return matcher.matches();
		}

	

}


