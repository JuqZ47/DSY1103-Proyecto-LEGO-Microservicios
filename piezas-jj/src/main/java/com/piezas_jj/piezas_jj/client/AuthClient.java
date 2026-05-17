package com.piezas_jj.piezas_jj.client;

import com.piezas_jj.piezas_jj.dto.AuthResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "AUTH-JJ", path = "/api/auth")
public interface AuthClient {
    @GetMapping("/validar")
    AuthResponseDTO validarToken(@RequestHeader("Authorization") String token);
}
