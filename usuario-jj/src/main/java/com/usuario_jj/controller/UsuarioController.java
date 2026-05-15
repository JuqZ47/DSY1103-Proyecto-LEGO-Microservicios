package com.usuario_jj.controller;

import com.usuario_jj.dto.UsuarioRequestDTO;
import com.usuario_jj.dto.UsuarioResponseDTO;
import com.usuario_jj.model.Usuario;
import com.usuario_jj.repository.UsuarioRepository;
import com.usuario_jj.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping("/registro")
    public ResponseEntity<UsuarioResponseDTO> crear(@Valid @RequestBody UsuarioRequestDTO dto) {
        return ResponseEntity.status(201).body(service.registrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(service.obtenerTodos(token));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscar(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(service.obtenerPorId(id, token));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        service.eliminar(id, token);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/{correo:.+}")
    public ResponseEntity<UsuarioResponseDTO> obtenerPorCorreo(
            @PathVariable("correo") String correo,
            @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(service.buscarPorCorreo(correo, token));
    }

    @GetMapping("/interno/{correo:.+}") // <--- Agrega el :.+ aquí también
    public ResponseEntity<UsuarioResponseDTO> buscarParaLogin(@PathVariable String correo) {
        return ResponseEntity.ok(service.buscarParaAutenticacion(correo));
    }

}
