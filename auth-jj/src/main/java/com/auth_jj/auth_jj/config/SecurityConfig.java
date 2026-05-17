package com.auth_jj.auth_jj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. Deshabilitamos CSRF porque el token JWT ya nos protege
                .csrf(csrf -> csrf.disable())

                // 2. Configuramos la sesión como STATELESS (fundamental para JWT)
                // Esto evita que el servidor guarde sesiones en memoria
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // 3. Definimos qué rutas son públicas y cuáles privadas
                .authorizeHttpRequests(auth -> auth
                        // Permitimos login, registro y AMBOS nombres de validación para evitar fallos
                        .requestMatchers(
                                "/api/auth/registrar",
                                "/api/auth/login",
                                "/api/auth/validar-token",
                                "/api/auth/validar"
                        ).permitAll()

                        // Cualquier otra ruta requiere que el usuario esté autenticado
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
