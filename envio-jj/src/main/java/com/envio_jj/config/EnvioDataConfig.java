package com.envio_jj.config;
import com.envio_jj.model.Envio;
import com.envio_jj.repository.EnvioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class EnvioDataConfig {
    @Bean
    CommandLineRunner initEnvioDatabase(EnvioRepository repository) {
        return args -> {
            // Solo insertamos si la tabla está vacía para no duplicar datos en la nube
            if (repository.count() == 0) {
                repository.saveAll(List.of(
                        new Envio(null, 1L, "Calle Falsa 123, Santiago", "PREPARANDO", LocalDateTime.now()),
                        new Envio(null, 2L, "Av. Libertad 456, Viña del Mar", "EN CAMINO", LocalDateTime.now()),
                        new Envio(null, 3L, "Pasaje El Sol 789, Casablanca", "ENTREGADO", LocalDateTime.now())
                ));
                System.out.println(">>> Éxito: Datos iniciales de Envío cargados en Oracle Cloud.");
            } else {
                System.out.println(">>> Aviso: La tabla de Envío ya contiene registros.");
            }
        };
    }
}
