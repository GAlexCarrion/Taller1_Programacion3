package com.itsqmet.Taller1.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String especie;
    private String raza;
    private int edad;

    // Relación: Muchas Mascotas pertenecen a un Cliente (Dueño)
    @ManyToOne
    @JoinColumn(name = "cliente_id") // Cambiado para evitar la ñ
    private Cliente cliente;

    // Nota: Asegúrate de tener creada la clase Cliente.java
}