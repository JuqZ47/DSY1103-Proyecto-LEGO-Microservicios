package com.auth_jj.auth_jj.controller;

import java.util.Map;

import com.auth_jj.auth_jj.dto.AuthRequestDTO;
import com.auth_jj.auth_jj.dto.AuthResponseDTO;
import com.auth_jj.auth_jj.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService autenticacionService;

    // POST /api/auth/registrar -> 201 Created
    @PostMapping("/registrar")
    public ResponseEntity<AuthResponseDTO> registrar(@Valid @RequestBody AuthRequestDTO dto) {
        return ResponseEntity.status(201).body(autenticacionService.registrar(dto));
    }

    // POST /api/auth/login -> 200 OK
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody Map<String, String> credenciales) {
        // Recibimos un mapa simple para el login
        return ResponseEntity.ok(autenticacionService.login(
                credenciales.get("username"),
                credenciales.get("password")));
    }
}
