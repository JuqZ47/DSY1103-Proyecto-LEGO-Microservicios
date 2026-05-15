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
    private final JwtService jwtService;

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

    // ResponseDTO
    private AuthResponseDTO mapToDTO(Autenticacion user, String token) {
        return new AuthResponseDTO(
                user.getId(),
                user.getIdUsuarioRef(),
                user.getUsername(),
                user.getRol(),
                token);
    }

    // REGISTRAR
    public AuthResponseDTO registrar(AuthRequestDTO dto) {

        Autenticacion user = new Autenticacion();
        user.setIdUsuarioRef(dto.getIdUsuarioRef());
        user.setUsername(dto.getUsername());

        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRol(dto.getRol());

        return mapToDTO(repository.save(user), null);
    }

    // LOGIN
    public AuthResponseDTO login(String username, String password) {
        // 1. Buscamos al usuario en la base de datos de Auth (por username)
        Autenticacion authUser = repository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 2. Validamos la contraseña (texto plano de Postman vs Hash de Oracle)
        if (!encoder.matches(password, authUser.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        // 3. Generamos el token usando el objeto de autenticación
        String token = jwtService.generarToken(authUser);

        // 4. Construimos el DTO con los nombres de campos exactos de tu imagen
        return AuthResponseDTO.builder()
                .id(authUser.getId())
                .idUsuarioRef(authUser.getIdUsuarioRef())
                .username(authUser.getUsername())
                .rol(authUser.getRol())
                .token(token)
                .build();
    }

}
