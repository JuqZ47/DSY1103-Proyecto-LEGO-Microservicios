package com.catalogo_jj.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.catalogo_jj.repository.CatalogoRepository;
import com.catalogo_jj.model.Lego;

import java.util.List;

@Configuration
public class CatalogoDataConfig {
    @Bean
    CommandLineRunner setupCatalogo(CatalogoRepository repository) {
        return args -> {
            //Solo insertaremos si la base de datos está vacía para evitar duplicados
            if (repository.count() == 0) {
                repository.saveAll(List.of(
                        new Lego(null, "Halcón Milenario Star Wars", "Star Wars", 1351, 159990.0),
                        new Lego(null, "Máquina de PAC-MAN Icons", "Icons", 2651, 249990.0),
                        new Lego(null, "Auto de F1 McLaren Technic", "Technic", 1432, 189990.0),
                        new Lego(null, "Castillo de Hogwarts", "Harry Potter", 6020, 449990.0),
                        new Lego(null, "London Architecture", "Architecture", 468, 39990.0)
                ));
                System.out.println(">>> Base de datos de Catálogo poblada con éxito.");
            }
        };
    }
}
