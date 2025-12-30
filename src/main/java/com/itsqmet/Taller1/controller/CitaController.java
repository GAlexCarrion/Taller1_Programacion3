package com.itsqmet.Taller1.controller;

import com.itsqmet.Taller1.entity.Cita;
import com.itsqmet.Taller1.service.CitaService;
import com.itsqmet.Taller1.service.MascotaService;
import com.itsqmet.Taller1.service.VeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private VeterinarioService veterinarioService;

    @GetMapping
    public String listarCitas(Model model) {
        model.addAttribute("citas", citaService.listarCitas());
        return "cita/lista";
    }

    @GetMapping("/nuevo")
    public String formularioCita(Model model) {
        model.addAttribute("cita", new Cita());
        model.addAttribute("mascotas", mascotaService.listarMascotas());
        model.addAttribute("veterinarios", veterinarioService.listarVeterinarios());
        return "cita/formulario";
    }

    @PostMapping("/guardar")
    public String guardarCita(@ModelAttribute Cita cita) {
        citaService.guardarCita(cita);
        return "redirect:/citas";
    }

    @GetMapping("/editar/{id}")
    public String editarCita(@PathVariable Long id, Model model) {
        citaService.buscarCitaPorId(id).ifPresent(cita -> model.addAttribute("cita", cita));
        model.addAttribute("mascotas", mascotaService.listarMascotas());
        model.addAttribute("veterinarios", veterinarioService.listarVeterinarios());
        return "cita/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCita(@PathVariable Long id) {
        citaService.eliminarCita(id);
        return "redirect:/citas";
    }
}