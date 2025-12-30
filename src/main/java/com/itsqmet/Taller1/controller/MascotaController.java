package com.itsqmet.Taller1.controller;

import com.itsqmet.Taller1.entity.Mascota;
import com.itsqmet.Taller1.service.MascotaService;
import com.itsqmet.Taller1.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String listarMascotas(Model model) {
        model.addAttribute("mascotas", mascotaService.listarMascotas());
        return "mascota/lista";
    }

    @GetMapping("/nuevo")
    public String formularioMascota(Model model) {
        model.addAttribute("mascota", new Mascota());
        model.addAttribute("clientes", clienteService.listarClientes());
        return "mascota/formulario";
    }

    @PostMapping("/guardar")
    public String guardarMascota(@ModelAttribute Mascota mascota) {
        mascotaService.guardarMascota(mascota);
        return "redirect:/mascotas";
    }

    @GetMapping("/editar/{id}")
    public String editarMascota(@PathVariable Long id, Model model) {
        mascotaService.buscarMascotaPorId(id).ifPresent(mascota -> model.addAttribute("mascota", mascota));
        model.addAttribute("clientes", clienteService.listarClientes());
        return "mascota/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarMascota(@PathVariable Long id) {
        mascotaService.eliminarMascota(id);
        return "redirect:/mascotas";
    }
}