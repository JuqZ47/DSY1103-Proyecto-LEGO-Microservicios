package com.loyalty_jj.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(
        name = "catalog-service",
        url = "http://localhost:8081"
)
public interface CatalogoFeignClient {

    @GetMapping("/api/catalog/{id}")
    Map<String, Object> obtenerSetPorId(@PathVariable Long id);
}