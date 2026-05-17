package com.piezas_jj.piezas_jj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PiezaRequestDTO {
    private String nombre;
    private String categoria;
    private String color;
    private Integer stock;
    private Double precio;
}
