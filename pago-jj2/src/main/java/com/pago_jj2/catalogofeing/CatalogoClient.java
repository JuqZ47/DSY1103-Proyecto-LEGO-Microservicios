package com.pago_jj2.catalogofeing;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "catalogo-jj", url = "http://localhost:8081/api/catalogo")
public interface CatalogoClient {

    @GetMapping("/{id}")
    Object obtenerLegoPorId(@PathVariable("id") Long id);
}
