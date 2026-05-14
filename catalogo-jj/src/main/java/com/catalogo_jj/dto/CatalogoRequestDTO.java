package com.catalogo_jj.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CatalogoRequestDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El tema es obligatorio")
    private String tema;

    @Positive(message = "Las piezas deben ser un número positivo")
    private Integer piezas;

    @Positive(message = "El precio debe ser un número positivo")
    private Double precio;
}
