package com.piezas_jj.piezas_jj.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String token;
    private String username;
    private String rol;
    private String mensaje;
}
