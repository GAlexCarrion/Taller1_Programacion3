package com.itsqmet.Taller1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nombre obligatorio")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre no puede tener números")
    private String nombre;

    private String especie;
    private Integer edad;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "mascota", fetch = FetchType.LAZY)
    private List<Cita> citas;
}