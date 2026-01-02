package com.itsqmet.Taller1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        //Rutas
                        .requestMatchers("/registro", "/login", "/css/**", "/js/**").permitAll()

                        // 2. Reglas restrictivas de ELIMINAR (Solo ADMIN)
                        // Se especifican las rutas una por una para evitar el error de comodines
                        .requestMatchers("/clientes/eliminar/**", "/mascotas/eliminar/**",
                                "/veterinarios/eliminar/**", "/citas/eliminar/**").hasRole("ADMIN")

                        // 3. Reglas de CREAR y EDITAR (ADMIN y VETERINARIO)
                        .requestMatchers("/clientes/nuevo", "/clientes/editar/**", "/clientes/guardar").hasAnyRole("ADMIN", "VETERINARIO")
                        .requestMatchers("/mascotas/nuevo", "/mascotas/editar/**", "/mascotas/guardar").hasAnyRole("ADMIN", "VETERINARIO")
                        .requestMatchers("/veterinarios/nuevo", "/veterinarios/editar/**", "/veterinarios/guardar").hasAnyRole("ADMIN", "VETERINARIO")
                        .requestMatchers("/citas/nuevo", "/citas/editar/**", "/citas/guardar").hasAnyRole("ADMIN", "VETERINARIO")

                        // 4. Vistas de consulta: Permitidas para los 3 ROLES
                        .requestMatchers("/", "/clientes", "/mascotas", "/veterinarios", "/citas").hasAnyRole("ADMIN", "VETERINARIO", "CLIENTE")

                        // Cualquier otra ruta requiere estar autenticado
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}