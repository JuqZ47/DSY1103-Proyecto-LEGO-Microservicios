package com.auth_jj.auth_jj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AuthResponseDTO {

    private Long id;
    private Long idUsuarioRef;
    private String username;
    private String rol;
    private String token;
    private String mensaje;
}
