package com.neoris.usuarios.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.neoris.usuarios.entity.Usuario;
import com.neoris.usuarios.jwt.JwtService;
import com.neoris.usuarios.repository.UsuarioRepositorio;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepositorio usuarioRepositorio;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(String name, String passowrd) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(name, passowrd));
        Usuario user=usuarioRepositorio.findByUsername(name).orElseThrow();
        String token=jwtService.getToken(name);
        return AuthResponse.builder()
            .token(token)
            .build();

    }

   

}
