package com.auth_jj.auth_jj.service;

import com.auth_jj.auth_jj.client.UsuarioClient;
import com.auth_jj.auth_jj.config.JwtService;
import com.auth_jj.auth_jj.dto.AuthRequestDTO;
import com.auth_jj.auth_jj.dto.AuthResponseDTO;
import com.auth_jj.auth_jj.dto.UsuarioResponseDTO;
import com.auth_jj.auth_jj.model.Autenticacion;
import com.auth_jj.auth_jj.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final AuthRepository repository;
    private final BCryptPasswordEncoder encoder;
    private final UsuarioClient usuarioClient;
    private final JwtService jwtService; // <- Usaremos este para validar

    public AuthResponseDTO validarExistenciaUsuario(AuthRequestDTO dto) {
        try {
            usuarioClient.verificarExistencia(dto.getIdUsuarioRef());
        } catch (Exception e) {
            throw new RuntimeException("No se puede crear autenticación: El usuario con ID "
                    + dto.getIdUsuarioRef() + " no existe.");
        }

        Autenticacion user = new Autenticacion();
        user.setIdUsuarioRef(dto.getIdUsuarioRef());
        user.setUsername(dto.getUsername());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRol(dto.getRol());

        return mapToDTO(repository.save(user), null);
    }

    // ResponseDTO unificado
    private AuthResponseDTO mapToDTO(Autenticacion user, String token) {
        return AuthResponseDTO.builder()
                .id(user.getId())
                .idUsuarioRef(user.getIdUsuarioRef())
                .username(user.getUsername())
                .rol(user.getRol())
                .token(token)
                .mensaje("Operación exitosa")
                .build();
    }

    public AuthResponseDTO registrar(AuthRequestDTO dto) {
        Autenticacion user = new Autenticacion();
        user.setIdUsuarioRef(dto.getIdUsuarioRef());
        user.setUsername(dto.getUsername());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRol(dto.getRol());

        return mapToDTO(repository.save(user), null);
    }

    public AuthResponseDTO login(String username, String password) {
        Autenticacion authUser = repository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!encoder.matches(password, authUser.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        // Generamos el JWT real
        String token = jwtService.generarToken(authUser);

        return mapToDTO(authUser, token);
    }

    public AuthResponseDTO validarToken(String headerToken) {
        if (headerToken == null || headerToken.isBlank()) {
            throw new RuntimeException("Token no proporcionado");
        }

        // 1. Limpiamos el Bearer
        String token = headerToken.startsWith("Bearer ") ? headerToken.substring(7) : headerToken;

        // 2. Usamos tu jwtService
        if (!jwtService.validarToken(token)) {
            throw new RuntimeException("Token inválido o expirado");
        }

        try {
            // 3. Extraemos el usuario y lo buscamos en tu repo
            String username = jwtService.extraerUsername(token);
            Autenticacion user = repository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            return mapToDTO(user, token);
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
