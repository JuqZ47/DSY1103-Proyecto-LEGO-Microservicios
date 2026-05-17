package com.envio_jj.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagoResponseDTO {
    private Long id;
    private Long idLegoReferencia;
    private Double monto;
    private String metodoPago;
    private String estadoPago;
    private LocalDateTime fechaPago;
}
