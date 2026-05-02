package com.catalogo_jj.catalogo_jj.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CatalogoRequestDTO {
    @NotBlank(message = "El nombre del set es obligatorio")
    private String nombre;

    @NotBlank(message = "El tema (Star Wars, Marvel, etc) es obligatorio")
    private String tema;

    @Min(value = 1, message = "El set debe tener al menos una pieza")
    private Integer piezas;

    @Positive(message = "El precio debe ser mayor a cero")
    private Double precio;
}
