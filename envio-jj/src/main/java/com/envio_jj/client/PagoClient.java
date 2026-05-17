package com.envio_jj.client;
import com.envio_jj.dto.PagoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "pago-jj2", path = "/api/pagos")
public interface PagoClient {
    @GetMapping("/{id}")
    PagoResponseDTO obtenerPagoPorId(@PathVariable Long id, @RequestHeader("Authorization") String token);
}
