package com.pago_jj2.config;

import com.pago_jj2.model.Pago;
import com.pago_jj2.repository.PagoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class PagoDataConfig {
    @Bean
    CommandLineRunner initDatabase(PagoRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.saveAll(List.of(
                        new Pago(null, 1L, 45000.0, "Tarjeta", LocalDateTime.now()),
                        new Pago(null, 2L, 89990.0, "Efectivo", LocalDateTime.now())
                ));
                System.out.println(">>> Datos de prueba insertados en la Wallet.");
            }
        };
    }
}
