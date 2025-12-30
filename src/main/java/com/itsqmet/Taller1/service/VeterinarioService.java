package com.itsqmet.Taller1.service;


import com.itsqmet.Taller1.entity.Veterinario;
import com.itsqmet.Taller1.repository.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VeterinarioService {

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    public List<Veterinario> listarVeterinarios() {
        return veterinarioRepository.findAll();
    }

    public void guardarVeterinario(Veterinario veterinario) {
        veterinarioRepository.save(veterinario);
    }

    public Optional<Veterinario> buscarVeterinarioPorId(Long id) {
        return veterinarioRepository.findById(id);
    }

    public void eliminarVeterinario(Long id) {
        veterinarioRepository.deleteById(id);
    }
}