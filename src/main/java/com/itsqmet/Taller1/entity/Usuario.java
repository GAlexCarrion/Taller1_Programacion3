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

    // Nombre de usuario único (usualmente el correo electrónico para el login)
    @Column(unique = true, nullable = false)
    private String username;

    // Contraseña que se almacenará encriptada mediante BCryptPasswordEncoder
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