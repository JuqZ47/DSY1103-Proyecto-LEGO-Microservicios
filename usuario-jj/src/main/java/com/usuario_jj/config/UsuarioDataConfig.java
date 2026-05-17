package com.usuario_jj.config;

import com.usuario_jj.model.Usuario;
import com.usuario_jj.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class UsuarioDataConfig {
    @Bean
    CommandLineRunner initUsuarios(UsuarioRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.saveAll(List.of(
                        // ID, Nombre, Correo, Password, Rol
                        new Usuario(null, "Admin Lego", "admin@legojj.cl", "admin123", "ADMIN"),
                        new Usuario(null, "Julian Romero", "julian@correo.cl", "julian123", "USER")
                ));
                System.out.println(">>> Usuarios de prueba (Admin/User) cargados.");
            }
        };
    }
}
