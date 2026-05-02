package com.catalogo_jj.catalogo_jj.controller;

import com.catalogo_jj.catalogo_jj.dto.CatalogoRequestDTO;
import com.catalogo_jj.catalogo_jj.dto.CatalogoResponseDTO;
import com.catalogo_jj.catalogo_jj.service.CatalogoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalogo")
@RequiredArgsConstructor
public class CatalogoController {
    private final CatalogoService catalogoService;


    @GetMapping
    public ResponseEntity<List<CatalogoResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(catalogoService.obtenerTodos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<CatalogoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return catalogoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<CatalogoResponseDTO> guardar(@Valid @RequestBody CatalogoRequestDTO request) {
        return new ResponseEntity<>(catalogoService.guardar(request), HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        catalogoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
