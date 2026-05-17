package com.inventario_jj.catalogofeing;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "catalogo-jj", path = "/api/catalogo")
public interface CatalogoClient {
    @GetMapping("/{id}")
    Object obtenerLegoPorId(@PathVariable Long id, @RequestHeader("Authorization") String token);

}
