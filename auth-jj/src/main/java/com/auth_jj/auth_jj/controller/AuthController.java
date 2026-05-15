package com.auth_jj.auth_jj.controller;

import java.util.Map;

import com.auth_jj.auth_jj.config.JwtService;
import com.auth_jj.auth_jj.dto.AuthRequestDTO;
import com.auth_jj.auth_jj.dto.AuthResponseDTO;
import com.auth_jj.auth_jj.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService autenticacionService;
    private final JwtService jwtService;

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
    @GetMapping("/validar")
    public ResponseEntity<AuthResponseDTO> validarToken(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");

        if (jwtService.validarToken(token)) {
            return ResponseEntity.ok(AuthResponseDTO.builder()
                    .token(token)
                    .username(jwtService.extraerUsername(token))
                    .rol(jwtService.extraerRol(token))
                    .build());
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
