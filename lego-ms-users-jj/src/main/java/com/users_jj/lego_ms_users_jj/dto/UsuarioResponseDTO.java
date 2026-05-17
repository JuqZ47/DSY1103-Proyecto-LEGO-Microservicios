package com.users_jj.lego_ms_users_jj.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioResponseDTO {
    private Long id;
    private String nombre;
    private String correo;
    private String rol;
}