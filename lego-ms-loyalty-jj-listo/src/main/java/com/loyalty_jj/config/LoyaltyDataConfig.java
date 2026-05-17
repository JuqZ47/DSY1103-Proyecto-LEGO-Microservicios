package com.loyalty_jj.config;

import com.loyalty_jj.model.Loyalty;
import com.loyalty_jj.repository.LoyaltyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class LoyaltyDataConfig {

    @Bean
    CommandLineRunner setupLoyalty(LoyaltyRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.saveAll(List.of(
                        new Loyalty(null, 1L, 500, "Bronce", 2),
                        new Loyalty(null, 2L, 1500, "Plata", 5),
                        new Loyalty(null, 3L, 3500, "Oro", 12),
                        new Loyalty(null, 4L, 50, "Novato", 0),
                        new Loyalty(null, 5L, 8000, "Diamante", 25)
                ));
                System.out.println(">>> Base de datos de Loyalty poblada con éxito.");
            } else {
                System.out.println(">>> El sistema de lealtad ya tiene registros, saltando inicialización.");
            }
        };
    }
}
