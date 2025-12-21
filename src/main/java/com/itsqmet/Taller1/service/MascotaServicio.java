package com.itsqmet.Taller1.service;


import com.itsqmet.Taller1.entity.Mascota;
import com.itsqmet.Taller1.repository.MascotaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MascotaServicio {
    @Autowired
    private MascotaRepositorio mascotaRepositorio;

    public List<Mascota> mostrarMascotas(){
        return mascotaRepositorio.findAll();
    }

    public List<Mascota> buscarPorNombre(String buscarMascota){
        if (buscarMascota == null || buscarMascota.isEmpty()){
            return mascotaRepositorio.findAll();
        } else {
            return mascotaRepositorio.findByNombreContainingIgnoreCase(buscarMascota);
        }
    }

    public Optional<Mascota> buscarMascotaId(Long id){
        return mascotaRepositorio.findById(id);
    }

    public Mascota guardarMascota(Mascota mascota){
        return mascotaRepositorio.save(mascota);
    }

    public void eliminarMascota(Long id){
        mascotaRepositorio.deleteById(id);
    }
}