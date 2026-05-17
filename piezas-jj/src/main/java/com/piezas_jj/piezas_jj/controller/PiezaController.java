package com.piezas_jj.piezas_jj.controller;

import com.piezas_jj.piezas_jj.client.AuthClient;
import com.piezas_jj.piezas_jj.dto.*;
import com.piezas_jj.piezas_jj.services.PiezaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/piezas")
@RequiredArgsConstructor
public class PiezaController {

    private final PiezaService service;
    private final AuthClient authClient;

    private void validar(String token) {
        if (token == null || token.isEmpty()) throw new RuntimeException("Token requerido");
        authClient.validarToken(token);
    }

    @GetMapping
    public ResponseEntity<List<PiezaResponseDTO>> listar(@RequestHeader("Authorization") String token) {
        validar(token);
        return ResponseEntity.ok(service.listarTodas());
    }

    @PostMapping
    public ResponseEntity<PiezaResponseDTO> guardar(@RequestHeader("Authorization") String token, @RequestBody PiezaRequestDTO dto) {
        validar(token);
        return ResponseEntity.status(201).body(service.crear(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        validar(token);
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
