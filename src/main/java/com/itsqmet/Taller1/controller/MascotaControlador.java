package com.itsqmet.Taller1.controller;

import com.itsqmet.Taller1.entity.Mascota;
import com.itsqmet.Taller1.entity.Usuario;
import com.itsqmet.Taller1.service.MascotaServicio;
import com.itsqmet.Taller1.service.UsuarioServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class MascotaControlador {

    @Autowired
    private MascotaServicio mascotaServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    // pagina DE INICIO
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // pagina PRINCIPAL
    @GetMapping("/mascotas")
    public String listarMascotas(@RequestParam(name="buscarMascota", required = false, defaultValue = "") String buscarMascota, Model model){
        List<Mascota> mascotas = mascotaServicio.buscarPorNombre(buscarMascota);
        model.addAttribute("buscarMascota", buscarMascota);
        model.addAttribute("mascotas", mascotas);
        return "pages/listaMascotas";
    }

    //--- parteMASCOTAS ---

    @GetMapping("/formularioMascota")
    public String formularioMascota(Model model){
        model.addAttribute("mascota", new Mascota());
        model.addAttribute("usuarios", usuarioServicio.mostrarUsuarios());
        return "pages/formularioMascota";
    }

    @PostMapping("/guardar-mascota")
    public String guardarMascota(@Valid @ModelAttribute("mascota") Mascota mascota, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("usuarios", usuarioServicio.mostrarUsuarios());
            return "pages/formularioMascota";
        }
        mascotaServicio.guardarMascota(mascota);
        return "redirect:/mascotas"; // va la url de lista mascotas
    }

    @GetMapping("/eliminar-mascota/{id}")
    public String eliminarMascota(@PathVariable Long id) {
        mascotaServicio.eliminarMascota(id);
        return "redirect:/mascotas";
    }

    @GetMapping("/editar-mascota/{id}")
    public String editarMascota(@PathVariable Long id, Model model) {
        Optional<Mascota> mascota = mascotaServicio.buscarMascotaId(id);
        model.addAttribute("mascota", mascota);
        model.addAttribute("usuarios", usuarioServicio.mostrarUsuarios());
        return "pages/formularioMascota";
    }

    //--- parte USUARIOS ---

    @GetMapping("/formularioUsuario")
    public String formularioUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "pages/formularioUsuario";
    }

    @PostMapping("/guardar-usuario")
    public String guardarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            return "pages/formularioUsuario";
        }
        usuarioServicio.guardarUsuario(usuario);
        return "redirect:/mascotas"; // registramos al usuario y  vamos a la lista de mascotas
    }
}