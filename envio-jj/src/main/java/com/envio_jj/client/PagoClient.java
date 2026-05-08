package com.envio_jj.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pago-jj", url = "http://localhost:8085/api/pagos")
public interface PagoClient {
    @GetMapping("/{id}")
    Object obtenerPagoPorId(@PathVariable("id") Long id);
}
