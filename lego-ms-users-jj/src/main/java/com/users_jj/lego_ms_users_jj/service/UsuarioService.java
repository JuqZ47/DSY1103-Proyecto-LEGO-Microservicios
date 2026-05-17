package com.users_jj.lego_ms_users_jj.service;

import com.users_jj.lego_ms_users_jj.client.AuthClient;
import com.users_jj.lego_ms_users_jj.dto.AuthResponseDTO;
import com.users_jj.lego_ms_users_jj.dto.UsuarioRequestDTO;
import com.users_jj.lego_ms_users_jj.dto.UsuarioResponseDTO;
import com.users_jj.lego_ms_users_jj.model.Usuario;
import com.users_jj.lego_ms_users_jj.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final AuthClient authClient;

    // --- CREATE (REGISTRO PÚBLICO) ---
    public UsuarioResponseDTO registrar(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setCorreo(dto.getCorreo());
        usuario.setPassword(dto.getPassword()); // Nota: Idealmente encriptar con BCrypt
        usuario.setRol(dto.getRol() != null ? dto.getRol() : "USER");

        return mapToDTO(repository.save(usuario));
    }

    // --- READ ALL (SOLO ADMIN) ---
    public List<UsuarioResponseDTO> obtenerTodos(String token) {
        AuthResponseDTO auth = authClient.validarToken(token);

        if (!"ADMIN".equals(auth.getRol())) {
            throw new RuntimeException("Acceso denegado: Solo el administrador puede ver la lista global.");
        }

        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // --- READ BY ID ---
    public UsuarioResponseDTO obtenerPorId(Long id, String token) {
        authClient.validarToken(token);
        return repository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    // --- DELETE ---
    public void eliminar(Long id, String token) {
        AuthResponseDTO auth = authClient.validarToken(token);
        if (!"ADMIN".equals(auth.getRol())) {
            throw new RuntimeException("No tienes permisos para eliminar usuarios.");
        }
        repository.deleteById(id);
    }

    // Mapeo usando @Builder de tu UsuarioResponseDTO
    private UsuarioResponseDTO mapToDTO(Usuario u) {
        return UsuarioResponseDTO.builder()
                .id(u.getId())
                .nombre(u.getNombre())
                .correo(u.getCorreo())
                .rol(u.getRol())
                .build();
    }
}