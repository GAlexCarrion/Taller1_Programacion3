package com.itsqmet.Taller1.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entidad que gestiona las credenciales de acceso y permisos del sistema.
 */
@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    /**
     * Definición de niveles de acceso (Cardinalidad de Roles).
     * Valores permitidos para este proyecto:
     * - ADMIN: Acceso total (incluye eliminación).
     * - VETERINARIO: Gestión médica (crear/editar citas).
     * - CLIENTE: Consulta de información personal.
     */
    private String rol;
}