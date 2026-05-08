package com.inventario_jj.inventario_jj.controller;

import com.inventario_jj.inventario_jj.dto.InventarioRequestDTO;
import com.inventario_jj.inventario_jj.dto.InventarioResponseDTO;
import com.inventario_jj.inventario_jj.service.InventarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
@RequiredArgsConstructor
public class InventarioController {
    private final InventarioService inventarioService;

    //GUARDAR
    @PostMapping
    public ResponseEntity<InventarioResponseDTO> crear(@Valid @RequestBody InventarioRequestDTO request) {
        return new ResponseEntity<>(inventarioService.guardarStock(request), HttpStatus.CREATED);
    }

    //LISTAR
    @GetMapping
    public ResponseEntity<List<InventarioResponseDTO>> listar() {
        return ResponseEntity.ok(inventarioService.obtenerTodo());
    }

    //ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<InventarioResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody InventarioRequestDTO request) {
        return ResponseEntity.ok(inventarioService.actualizar(id, request));
    }

    //ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        inventarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
