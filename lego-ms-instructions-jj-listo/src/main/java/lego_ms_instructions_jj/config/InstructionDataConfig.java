package lego_ms_instructions_jj.config;

import lego_ms_instructions_jj.model.Instruction;
import lego_ms_instructions_jj.repository.InstructionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class InstructionDataConfig {

    @Bean
    CommandLineRunner setupInstructions(InstructionRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.saveAll(List.of(
                        new Instruction(null, 1L, "Manual de Construcción Básica", "Guía paso a paso para armar figuras simples con ladrillos 2x4.", "http://legojj.cl/instrucciones/basico-1.pdf", 12),
                        new Instruction(null, 2L, "Guía Avanzada Technic", "Instrucciones detalladas para el montaje del sistema de engranajes y motor V8.", "http://legojj.cl/instrucciones/technic-v8.pdf", 45),
                        new Instruction(null, 3L, "Manual Transbordador Espacial", "Pasos de ensamblaje para la cabina y los propulsores del set espacial.", "http://legojj.cl/instrucciones/space-shuttle.pdf", 30),
                        new Instruction(null, 1L, "Guía de Colores y Combinaciones", "Consejos para mejorar tus creaciones usando diferentes paletas de colores.", "http://legojj.cl/instrucciones/colores.pdf", 8),
                        new Instruction(null, 4L, "Manual de Usuario - Robot NXT", "Configuración inicial y primeros pasos para la programación del bloque inteligente.", "http://legojj.cl/instrucciones/nxt-robot.pdf", 60)
                ));
                System.out.println(">>> Base de datos de Instrucciones poblada con éxito.");
            } else {
                System.out.println(">>> El sistema de instrucciones ya tiene datos, saltando inicialización.");
            }
        };
    }
}
