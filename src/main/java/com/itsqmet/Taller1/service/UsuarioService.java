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
        // 1. Encriptamos la clave para que no se vea
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        // 2. Seguridad: Forzamos que el rol sea 'USER' para registros nuevos
        // con eso evitamos que alguien manipule el formulario para ser ADMIN
        usuario.setRol("USER");

        usuarioRepository.save(usuario);
    }
}