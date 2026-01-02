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

    // Muestra la página de inicio de sesión
    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    // Prepara el formulario de registro enviando un objeto Usuario vacío
    @GetMapping("/registro")
    public String registroForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "auth/registro";
    }

    // Recibe los datos del formulario, incluyendo el ROL seleccionado
    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario) {
        // Delegamos la encriptación y el guardado al servicio
        usuarioService.registrar(usuario);
        // Redirigimos al login con un mensaje de éxito
        return "redirect:/login?success";
    }
}