package com.users_jj.lego_ms_users_jj.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioRequestDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Email(message = "Debe ser un correo válido")
    private String correo;

    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;

    private String rol;
}