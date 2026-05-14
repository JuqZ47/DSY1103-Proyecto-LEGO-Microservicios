package com.pago_jj2.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private Long idAuth;
    private Long idUsuarioRef;
    private String rol;
    private String token;
}
