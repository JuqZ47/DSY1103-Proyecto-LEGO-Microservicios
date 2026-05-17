package com.users_jj.lego_ms_users_jj.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// name: Nombre del microservicio en Eureka
// url: URL base para pruebas locales
@FeignClient(name = "catalogo-jj", url = "http://localhost:8081/api/catalogo")
public interface CatalogoClient {

    @GetMapping("/{id}")
    Object obtenerLegoPorId(@PathVariable("id") Long id);
}