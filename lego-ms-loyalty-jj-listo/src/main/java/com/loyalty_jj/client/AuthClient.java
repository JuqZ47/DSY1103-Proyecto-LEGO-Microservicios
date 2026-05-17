package com.loyalty_jj.client;

import com.loyalty_jj.dto.AuthResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-jj", path = "/api/auth")
public interface AuthClient {

    @GetMapping("/validar")
    AuthResponseDTO validarToken(@RequestHeader("Authorization") String token);
}
