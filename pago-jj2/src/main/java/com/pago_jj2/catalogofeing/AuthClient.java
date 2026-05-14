package com.pago_jj2.catalogofeing;

import com.pago_jj2.dto.AuthResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-jj", path = "/api/auth")
public interface AuthClient {
    @GetMapping("/validar")
    AuthResponseDTO validarToken(@RequestHeader("Authorization") String token);
}
