package com.neoris.usuarios.usuarios;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.neoris.usuarios.entity.Phone;
import com.neoris.usuarios.entity.Usuario;
import com.neoris.usuarios.repository.UsuarioRepositorio;
import com.neoris.usuarios.request.PhoneRequest;
import com.neoris.usuarios.request.UsuarioRequest;
import com.neoris.usuarios.service.impl.UsuarioServiceimpl;


@ExtendWith(MockitoExtension.class)
class UsuariosApplicationTests {
	
	@Mock
	private UsuarioRepositorio usuarioRepositorio;
	
	@InjectMocks
	private UsuarioServiceimpl usuarioService;
	
	@DisplayName("Se ingresa un registro en la Bd")

	@Test
	void insertarRegistroTest() {
		
		PhoneRequest phone = new PhoneRequest(111, 222, 333);	
		List<Phone> listaPhones  = new  ArrayList<Phone>(); 	
		List<PhoneRequest> phones = new  ArrayList<PhoneRequest>(); 	
		phones.add(phone);
		UsuarioRequest usr= new UsuarioRequest("nombre", "gabriel2gmail.com", "password", phones); 		
		
		Usuario user = new Usuario();
        user.setUsername(usr.getName());
        user.setEmail(usr.getEmail());
        user.setPassword(usr.getPassword());
        user.setPhone(listaPhones);
        user.setFechaCreacion(new Date());
        user.setFechaIngreso(new Date());
        user.setFechaModificacion(new Date());
		try {
			assertNotNull(user.getUsername(), "El campo name no debe ser nulo");
			assertNotNull(user.getEmail(), "El campo Email no debe ser nulo");
			assertNotNull(user.getPassword(), "El campo password no debe ser nulo");
	        assertFalse(user.getPhone().isEmpty(), "La lista de phones debe contener al menos un teléfono");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
