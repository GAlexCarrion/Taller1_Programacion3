package com.itsqmet.Taller1.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Veterinario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String especialidad;
    private String licencia;
    private String telefono;

    //relacion: un Veterinario atiende muchas Citas
    @OneToMany(mappedBy = "veterinario", cascade = CascadeType.ALL)
    private List<Cita> citas;
}