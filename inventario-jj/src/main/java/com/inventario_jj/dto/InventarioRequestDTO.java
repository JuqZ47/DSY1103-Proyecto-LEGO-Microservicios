package com.inventario_jj.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InventarioRequestDTO {
    @NotNull(message = "El ID de referencia del LEGO es obligatorio")
    private Long idLegoReferencia;

    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private Integer cantidad;

    @NotBlank(message = "La ubicación en bodega es obligatoria")
    private String ubicacion;
}
