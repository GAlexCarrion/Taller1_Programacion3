package com.itsqmet.Taller1.repository;


import com.itsqmet.Taller1.entity.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MascotaRepositorio extends JpaRepository<Mascota, Long> {
    List<Mascota> findByNombreContainingIgnoreCase(String nombre);
    List<Mascota> findByUsuarioId(Long usuarioId);
}