package com.usuario_jj.controller;

import com.usuario_jj.dto.UsuarioRequestDTO;
import com.usuario_jj.dto.UsuarioResponseDTO;
import com.usuario_jj.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @GetMapping
    public List<UsuarioResponseDTO> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public UsuarioResponseDTO crear(@RequestBody UsuarioRequestDTO request) {
        return service.registrar(request);
    }

    @PutMapping("/{id}")
    public UsuarioResponseDTO editar(@PathVariable Long id, @RequestBody UsuarioRequestDTO request) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
