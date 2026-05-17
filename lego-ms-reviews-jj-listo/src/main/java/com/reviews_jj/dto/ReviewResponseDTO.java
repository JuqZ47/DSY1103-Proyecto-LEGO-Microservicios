package com.reviews_jj.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDTO {

    private Long id;
    private Long usuarioId;
    private Long setId;
    private String comentario;
    private Integer calificacion;
    private LocalDateTime fecha;
}