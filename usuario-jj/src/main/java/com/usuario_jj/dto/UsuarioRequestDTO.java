package com.usuario_jj.dto;

import lombok.Data;

@Data
public class UsuarioRequestDTO {
    private String nombre;
    private String correo;
    private String password;
    private String rol;
}
