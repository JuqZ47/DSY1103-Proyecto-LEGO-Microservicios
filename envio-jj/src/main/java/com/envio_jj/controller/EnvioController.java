package com.envio_jj.controller;
import com.envio_jj.dto.EnvioRequestDTO;
import com.envio_jj.dto.EnvioResponseDTO;
import com.envio_jj.service.EnvioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/envios")
@RequiredArgsConstructor
public class EnvioController {

    private final EnvioService service;

    @PostMapping
    public ResponseEntity<EnvioResponseDTO> crear(@RequestBody EnvioRequestDTO dto,
                                                  @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(201).body(service.registrarEnvio(dto, token));
    }

    @GetMapping
    public ResponseEntity<List<EnvioResponseDTO>> listar(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(service.obtenerTodos(token));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<EnvioResponseDTO> cambiarEstado(@PathVariable Long id,
                                                          @RequestBody Map<String, String> body,
                                                          @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(service.actualizarEstado(id, body.get("estado"), token));
    }
}
