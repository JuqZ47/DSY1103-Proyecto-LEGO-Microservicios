package com.catalogo_jj.catalogo_jj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatalogoResponseDTO {
    private Long id;
    private String nombre;
    private String tema;
    private Integer piezas;
    private Double precio;
}
