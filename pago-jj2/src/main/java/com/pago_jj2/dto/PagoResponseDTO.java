package com.pago_jj2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagoResponseDTO {
    private Long id;
    private Long idLegoReferencia;
    private Double monto;
    private String metodoPago;
    private LocalDateTime fechaPago;
}
