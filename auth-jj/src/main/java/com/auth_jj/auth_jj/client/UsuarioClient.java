package com.auth_jj.auth_jj.client;

import com.auth_jj.auth_jj.dto.UsuarioResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usuario-jj", path = "/api/usuarios")
public interface UsuarioClient {

    @GetMapping("/interno/{correo}")
    UsuarioResponseDTO obtenerPorCorreo(@PathVariable("correo") String correo);

    @GetMapping("/{id}")
    Object verificarExistencia(@PathVariable("id") Long id);
}
