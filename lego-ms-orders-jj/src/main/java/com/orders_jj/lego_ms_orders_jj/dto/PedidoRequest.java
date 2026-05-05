package com.orders_jj.lego_ms_orders_jj.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PedidoRequest {

    @NotNull(message = "El idUsuario es obligatorio")
    private Long idUsuario;

    @NotNull(message = "El idSet es obligatorio")
    private Long idSet;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer cantidad;

    @NotNull(message = "El total es obligatorio")
    @Min(value = 1, message = "El total debe ser mayor a 0")
    private Double total;
}