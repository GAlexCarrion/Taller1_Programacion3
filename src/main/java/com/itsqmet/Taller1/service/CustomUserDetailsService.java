package com.itsqmet.Taller1.service;

import com.itsqmet.Taller1.entity.Usuario;
import com.itsqmet.Taller1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Servicio encargado de cargar los datos del usuario desde la base de datos
 * para el proceso de autenticación de Spring Security.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Buscamos al usuario por su username (email)
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con el nombre '" + username + "' no existe."));

        // 2. Construimos el objeto UserDetails que entiende Spring Security.
        // .roles() añade automáticamente el prefijo "ROLE_" al valor (ADMIN, CLIENTE, etc.)
        // Esto es más limpio que concatenar strings manualmente.
        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getRol()) // Convierte "ADMIN" en "ROLE_ADMIN" internamente
                .build();
    }
}