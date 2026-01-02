package com.itsqmet.Taller1.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString; // Añadir importación

@Entity
@Data
@NoArgsConstructor
@ToString(exclude = "cliente") // <--- ESTO EVITA EL BUCLE CON EL DUEÑO
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String especie;
    private String raza;
    private int edad;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}