package com.piezas_jj.piezas_jj.config;

import com.piezas_jj.piezas_jj.model.Pieza;
import com.piezas_jj.piezas_jj.repository.PiezaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PiezaDataConfig {

    @Bean
    CommandLineRunner setupPiezas(PiezaRepository repository) {
        return args -> {
            // Solo insertamos si no hay piezas registradas en la base de datos
            if (repository.count() == 0) {
                repository.saveAll(List.of(
                        new Pieza(null, "Ladrillo 2x4", "Básico", "Rojo", 100, 0.50),
                        new Pieza(null, "Placa 1x1", "Básico", "Azul", 500, 0.10),
                        new Pieza(null, "Viga Technic 13L", "Technic", "Gris", 50, 1.20),
                        new Pieza(null, "Cabeza de Minifigura", "Minifig", "Amarillo", 30, 2.00),
                        new Pieza(null, "Rueda 30mm", "Technic", "Negro", 80, 0.80)
                ));
                System.out.println(">>> Base de datos de Piezas poblada con éxito.");
            } else {
                System.out.println(">>> El catálogo de piezas ya tiene datos, saltando inicialización.");
            }
        };
    }
}
