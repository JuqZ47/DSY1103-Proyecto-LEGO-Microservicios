package com.envio_jj.dto;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class EnvioResponseDTO {
    private Long id;
    private String direccionDestino;
    private String estado;
    private LocalDateTime fechaDespacho;
}
