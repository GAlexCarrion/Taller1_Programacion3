package com.itsqmet.Taller1.controller;

import com.itsqmet.Taller1.entity.Usuario;
import com.itsqmet.Taller1.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    // Muestra la pgina de inicio
    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    // Prepara el formulario de registro enviando un objeto usuario
    @GetMapping("/registro")
    public String registroForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "auth/registro";
    }

    // Recibe los datos del formulario, incluyendo el rol
    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.registrar(usuario);
        return "redirect:/login?success";
    }
}