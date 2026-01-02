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

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));


        if (usuario.getRol() == null || usuario.getRol().isEmpty()) {
            usuario.setRol("CLIENTE");
        }

        usuarioRepository.save(usuario);
    }
}