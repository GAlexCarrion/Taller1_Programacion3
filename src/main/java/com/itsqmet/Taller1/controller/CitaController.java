package com.itsqmet.Taller1.controller;

import com.itsqmet.Taller1.entity.Cita;
import com.itsqmet.Taller1.service.CitaService;
import com.itsqmet.Taller1.service.MascotaService;
import com.itsqmet.Taller1.service.VeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // Asegúrate que sea este y NO @RestController
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private VeterinarioService veterinarioService;

    // LISTAR CITAS
    @GetMapping
    public String listarCitas(Model model) {
        model.addAttribute("citas", citaService.listarCitas());
        return "cita/lista";
    }

    // MOSTRAR FORMULARIO
    @GetMapping("/nuevo")
    public String formularioCita(Model model) {
        model.addAttribute("cita", new Cita());
        model.addAttribute("mascotas", mascotaService.listarMascotas());
        model.addAttribute("veterinarios", veterinarioService.listarVeterinarios());
        // Debe existir: src/main/resources/templates/cita/formulario.html
        return "cita/formulario";
    }

    // GUARDAR O ACTUALIZAR CITA
    @PostMapping("/guardar")
    public String guardarCita(@ModelAttribute("cita") Cita cita) {
        citaService.guardarCita(cita);
        return "redirect:/citas";
    }

    // EDITAR CITA EXISTENTE
    @GetMapping("/editar/{id}")
    public String editarCita(@PathVariable Long id, Model model) {
        Optional<Cita> citaOptional = citaService.buscarCitaPorId(id);

        if (citaOptional.isPresent()) {
            model.addAttribute("cita", citaOptional.get());
            model.addAttribute("mascotas", mascotaService.listarMascotas());
            model.addAttribute("veterinarios", veterinarioService.listarVeterinarios());
            return "cita/formulario";
        } else {
            return "redirect:/citas";
        }
    }

    // ELIMINAR CITA
    @GetMapping("/eliminar/{id}")
    public String eliminarCita(@PathVariable Long id) {
        citaService.eliminarCita(id);
        return "redirect:/citas";
    }
}