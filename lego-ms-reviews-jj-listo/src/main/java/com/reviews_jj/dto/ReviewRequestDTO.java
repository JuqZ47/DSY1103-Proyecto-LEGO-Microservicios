package com.reviews_jj.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestDTO {

    @NotNull
    private Long usuarioId;

    @NotNull
    private Long setId;

    @NotBlank
    private String comentario;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer calificacion;
}