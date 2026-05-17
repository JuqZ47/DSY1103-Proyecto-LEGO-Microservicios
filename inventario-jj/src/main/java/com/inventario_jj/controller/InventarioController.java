package com.inventario_jj.controller;

import com.inventario_jj.dto.InventarioRequestDTO;
import com.inventario_jj.dto.InventarioResponseDTO;
import com.inventario_jj.service.InventarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService service;

    @PostMapping
    public ResponseEntity<InventarioResponseDTO> actualizarStock(
            @Valid @RequestBody InventarioRequestDTO dto,
            @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(service.guardarStock(dto, token));
    }

    @GetMapping
    public ResponseEntity<List<InventarioResponseDTO>> listarTodo(
            @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(service.obtenerTodos(token));
    }

    @GetMapping("/lego/{idLego}")
    public ResponseEntity<InventarioResponseDTO> buscarPorLego(
            @PathVariable Long idLego,
            @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(service.obtenerPorLegoId(idLego, token));
    }
}
