package com.inventario_jj.inventario_jj.catalogofeing;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-catalogo", path = "/api/catalogo")
public interface CatalogoClient {
    @GetMapping("/{id}")
    Object obtenerLegoPorId(@PathVariable("id") Long id);

}
