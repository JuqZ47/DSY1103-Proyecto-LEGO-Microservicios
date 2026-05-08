package com.catalogo_jj.catalogo_jj.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CatalogoResponseDTO {
    private Long id;
    private String nombre;
    private String tema;
    private Integer piezas;
    private Double precio;
}
