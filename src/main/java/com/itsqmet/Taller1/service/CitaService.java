package com.itsqmet.Taller1.service;


import com.itsqmet.Taller1.entity.Cita;
import com.itsqmet.Taller1.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    public List<Cita> listarCitas() {
        return citaRepository.findAll();
    }

    public void guardarCita(Cita cita) {
        citaRepository.save(cita);
    }

    public Optional<Cita> buscarCitaPorId(Long id) {
        return citaRepository.findById(id);
    }

    public void eliminarCita(Long id) {
        citaRepository.deleteById(id);
    }
}