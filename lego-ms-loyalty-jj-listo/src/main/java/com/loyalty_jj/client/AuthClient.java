package com.loyalty_jj.client;

import com.loyalty_jj.dto.AuthResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-jj", url = "http://localhost:8080")
public interface AuthClient {

    @GetMapping("/auth/validar")
    AuthResponseDTO validarToken(@RequestHeader("Authorization") String token);
}
