package com.inventario_jj.config;

import com.inventario_jj.model.Inventario;
import com.inventario_jj.repository.InventarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class InventarioDataConfig {
    @Bean
    CommandLineRunner setupInventario(InventarioRepository repository) {
        return args -> {
            // Solo insertamos si no hay Inventario registrado
            if (repository.count() == 0) {
                repository.saveAll(List.of(
                        // El segundo parámetro es el ID del LEGO en el catálogo
                        new Inventario(null, 1L, 10, "Bodega Central - Valparaíso"),
                        new Inventario(null, 2L, 5, "Tienda Casablanca"),
                        new Inventario(null, 3L, 15, "Bodega Norte"),
                        new Inventario(null, 4L, 2, "Exhibición Principal"),
                        new Inventario(null, 5L, 20, "Bodega Central - Valparaíso")
                ));
                System.out.println(">>> Base de datos de Inventario poblada con éxito.");
            } else {
                System.out.println(">>> El inventario ya tiene datos, saltando inicialización.");
            }
        };
    }
}
