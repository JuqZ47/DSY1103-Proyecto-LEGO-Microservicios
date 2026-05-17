package com.catalogo_jj.controller;

import com.catalogo_jj.dto.CatalogoRequestDTO;
import com.catalogo_jj.dto.CatalogoResponseDTO;
import com.catalogo_jj.service.CatalogoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalogo")
@RequiredArgsConstructor
public class CatalogoController {

    private final CatalogoService service;

    @PostMapping
    public ResponseEntity<CatalogoResponseDTO> crear(@Valid @RequestBody CatalogoRequestDTO dto,
                                                     @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(201).body(service.crear(dto, token));
    }

    @GetMapping
    public ResponseEntity<List<CatalogoResponseDTO>> listar(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(service.obtenerTodos(token));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatalogoResponseDTO> buscar(@PathVariable Long id,
                                                      @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(service.obtenerPorId(id, token));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CatalogoResponseDTO> editar(@PathVariable Long id,
                                                      @Valid @RequestBody CatalogoRequestDTO dto,
                                                      @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(service.actualizar(id, dto, token));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id,
                                       @RequestHeader("Authorization") String token) {
        service.eliminar(id, token);
        return ResponseEntity.noContent().build();
    }
}
