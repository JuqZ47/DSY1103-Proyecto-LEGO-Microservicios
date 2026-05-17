package com.reviews_jj.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "lego-ms-users", url = "http://localhost:8084")
public interface UserClient {

    @GetMapping("/api/usuarios/{id}")
    Map<String, Object> obtenerUsuarioPorId(@PathVariable Long id);
}