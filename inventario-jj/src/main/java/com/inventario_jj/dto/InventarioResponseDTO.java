package com.inventario_jj.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventarioResponseDTO {
    private Long id;
    private Long idLegoReferencia;
    private Integer cantidad;
    private String ubicacion;
}
