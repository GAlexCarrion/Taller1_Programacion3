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
                        // 1. Rutas públicas
                        .requestMatchers("/registro", "/login", "/css/**", "/js/**").permitAll()

                        // 2. Seguridad para CLIENTES
                        .requestMatchers("/clientes/eliminar/**").hasRole("ADMIN")
                        .requestMatchers("/clientes/nuevo", "/clientes/editar/**", "/clientes/guardar").hasAnyRole("ADMIN", "USER")

                        // 3. Seguridad para CITAS
                        .requestMatchers("/citas/eliminar/**").hasRole("ADMIN")
                        .requestMatchers("/citas/nuevo", "/citas/editar/**", "/citas/guardar").hasAnyRole("ADMIN", "USER")

                        // 4. Todo lo demás requiere login
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        // Eliminamos el exceptionHandling para no requerir la página 403.html

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}