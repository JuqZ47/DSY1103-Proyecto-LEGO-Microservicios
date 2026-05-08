package com.pago_jj2.dto;

import lombok.Data;

@Data
public class PagoRequestDTO {
    private Long idLegoReferencia;
    private Double monto;
    private String metodoPago;
}
