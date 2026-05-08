package com.envio_jj.controller;
import com.envio_jj.dto.EnvioRequestDTO;
import com.envio_jj.dto.EnvioResponseDTO;
import com.envio_jj.service.EnvioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/envios")
@RequiredArgsConstructor
public class EnvioController {
    private final EnvioService service;


    @GetMapping
    public ResponseEntity<List<EnvioResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }


    @PostMapping
    public ResponseEntity<EnvioResponseDTO> guardar(@RequestBody EnvioRequestDTO request) {
        return new ResponseEntity<>(service.registrarEnvio(request), HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarEnvio(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnvioResponseDTO> actualizar(@PathVariable Long id, @RequestBody EnvioRequestDTO request) {
        return ResponseEntity.ok(service.actualizarEnvio(id, request));
    }
}
