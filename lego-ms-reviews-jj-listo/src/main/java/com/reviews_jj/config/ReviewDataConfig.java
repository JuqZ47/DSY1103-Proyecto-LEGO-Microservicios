package com.reviews_jj.config;

import com.reviews_jj.model.Review;
import com.reviews_jj.repository.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class ReviewDataConfig {

    @Bean
    CommandLineRunner setupReviews(ReviewRepository repository) {
        return args -> {
            // Solo insertamos si no hay reseñas registradas
            if (repository.count() == 0) {
                repository.saveAll(List.of(
                        // Orden del constructor: id, usuarioId, setId, comentario, calificacion, fecha
                        new Review(null, 10L, 1L, "¡Increíble set! El detalle de las piezas es excelente.", 5, LocalDateTime.now()),
                        new Review(null, 20L, 2L, "Faltó una pieza pequeña, pero el diseño general es asombroso.", 4, LocalDateTime.now().minusDays(1)),
                        new Review(null, 30L, 1L, "Un poco difícil de armar para niños pequeños, pero muy divertido.", 4, LocalDateTime.now().minusHours(5)),
                        new Review(null, 40L, 3L, "La mejor compra de este año. La calidad de Lego nunca falla.", 5, LocalDateTime.now().minusWeeks(1)),
                        new Review(null, 10L, 4L, "Me encantó el color de los bloques de este set.", 5, LocalDateTime.now().minusMinutes(30))
                ));
                System.out.println(">>> Base de datos de Reviews poblada con éxito.");
            } else {
                System.out.println(">>> El sistema de reseñas ya tiene datos, saltando inicialización.");
            }
        };
    }
}
