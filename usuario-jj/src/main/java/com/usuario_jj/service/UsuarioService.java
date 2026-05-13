package com.usuario_jj.service;

import com.usuario_jj.dto.UsuarioRequestDTO;
import com.usuario_jj.dto.UsuarioResponseDTO;
import com.usuario_jj.model.Usuario;
import com.usuario_jj.repository.UsuarioRepository;
import com.usuario_jj.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    public List<UsuarioResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO registrar(UsuarioRequestDTO request) {
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setCorreo(request.getCorreo());
        usuario.setPassword(request.getPassword());
        usuario.setRol(request.getRol());

        return convertirADTO(repository.save(usuario));
    }

    public UsuarioResponseDTO actualizar(Long id, UsuarioRequestDTO request) {
        return repository.findById(id).map(u -> {
            u.setNombre(request.getNombre());
            u.setCorreo(request.getCorreo());
            u.setRol(request.getRol());
            return convertirADTO(repository.save(u));
        }).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    private UsuarioResponseDTO convertirADTO(Usuario u) {
        return UsuarioResponseDTO.builder()
                .id(u.getId())
                .nombre(u.getNombre())
                .correo(u.getCorreo())
                .rol(u.getRol())
                .build();
    }
}
