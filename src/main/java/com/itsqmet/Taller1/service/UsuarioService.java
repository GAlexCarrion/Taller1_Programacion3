package com.itsqmet.Taller1.service;

import com.itsqmet.Taller1.entity.Usuario;
import com.itsqmet.Taller1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void registrar(Usuario usuario) {
        // 1. Encriptamos la clave (Obligatorio para Spring Security)
        // Esto transforma "123" en algo como "$2a$10$..."
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        // 2. Lógica de Roles:
        // Si el objeto usuario ya trae un rol (desde el select del formulario), lo guardamos.
        // Si por alguna razón el rol llega vacío, le asignamos "CLIENTE" por defecto por seguridad.
        if (usuario.getRol() == null || usuario.getRol().isEmpty()) {
            usuario.setRol("CLIENTE");
        }

        // 3. Guardamos el usuario con su rol final (ADMIN, VETERINARIO o CLIENTE)
        usuarioRepository.save(usuario);
    }
}