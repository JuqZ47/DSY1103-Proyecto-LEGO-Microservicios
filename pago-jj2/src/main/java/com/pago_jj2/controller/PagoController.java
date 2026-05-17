package com.pago_jj2.controller;

import com.pago_jj2.dto.PagoRequestDTO;
import com.pago_jj2.dto.PagoResponseDTO;
import com.pago_jj2.service.PagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;

    @PostMapping
    public ResponseEntity<PagoResponseDTO> crear(@RequestBody PagoRequestDTO dto, @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(201).body(pagoService.registrarPago(dto, token));
    }

    @GetMapping
    public ResponseEntity<List<PagoResponseDTO>> listar(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(pagoService.obtenerTodos(token));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoResponseDTO> buscarPorId(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(pagoService.obtenerPorId(id, token));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        pagoService.eliminarPago(id, token);
        return ResponseEntity.noContent().build();
    }
}
