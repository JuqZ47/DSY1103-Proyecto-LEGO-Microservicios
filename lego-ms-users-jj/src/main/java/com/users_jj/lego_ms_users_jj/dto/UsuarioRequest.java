package com.users_jj.lego_ms_users_jj.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Email(message = "El correo debe tener formato valido")
    @NotBlank(message = "El correo es obligatorio")
    private String correo;

    @NotBlank(message = "La password es obligatoria")
    private String password;

    @NotBlank(message = "El rol es obligatorio")
    private String rol;
}