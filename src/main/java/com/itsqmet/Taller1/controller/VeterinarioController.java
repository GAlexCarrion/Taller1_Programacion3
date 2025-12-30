package com.itsqmet.Taller1.controller;

import com.itsqmet.Taller1.entity.Veterinario;
import com.itsqmet.Taller1.service.VeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/veterinarios")
public class VeterinarioController {

    @Autowired
    private VeterinarioService veterinarioService;

    @GetMapping
    public String listarVeterinarios(Model model) {
        model.addAttribute("veterinarios", veterinarioService.listarVeterinarios());
        return "veterinario/lista";
    }

    @GetMapping("/nuevo")
    public String formularioVeterinario(Model model) {
        model.addAttribute("veterinario", new Veterinario());
        return "veterinario/formulario";
    }

    @PostMapping("/guardar")
    public String guardarVeterinario(@ModelAttribute Veterinario veterinario) {
        veterinarioService.guardarVeterinario(veterinario);
        return "redirect:/veterinarios";
    }

    @GetMapping("/editar/{id}")
    public String editarVeterinario(@PathVariable Long id, Model model) {
        veterinarioService.buscarVeterinarioPorId(id).ifPresent(v -> model.addAttribute("veterinario", v));
        return "veterinario/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarVeterinario(@PathVariable Long id) {
        veterinarioService.eliminarVeterinario(id);
        return "redirect:/veterinarios";
    }
}