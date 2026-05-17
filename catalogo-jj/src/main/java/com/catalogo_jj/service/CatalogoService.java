package com.catalogo_jj.service;

import com.catalogo_jj.catalogofeing.AuthClient;
import com.catalogo_jj.dto.AuthResponseDTO;
import com.catalogo_jj.dto.CatalogoRequestDTO;
import com.catalogo_jj.dto.CatalogoResponseDTO;
import com.catalogo_jj.model.Lego; // Asegúrate que tu entidad se llame Lego
import com.catalogo_jj.repository.CatalogoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogoService {

    private final CatalogoRepository repository;
    private final AuthClient authClient;

    // --- CREATE ---
    public CatalogoResponseDTO crear(CatalogoRequestDTO dto, String token) {
        AuthResponseDTO auth = authClient.validarToken(token);
        if (!"ADMIN".equals(auth.getRol())) {
            throw new RuntimeException("Acceso denegado: Solo el administrador puede crear sets.");
        }

        Lego lego = new Lego();
        lego.setNombre(dto.getNombre());
        lego.setTema(dto.getTema());
        lego.setPiezas(dto.getPiezas());
        lego.setPrecio(dto.getPrecio());

        return mapToDTO(repository.save(lego));
    }

    // --- READ ALL ---
    public List<CatalogoResponseDTO> obtenerTodos(String token) {
        authClient.validarToken(token); // Validación básica de sesión
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // --- READ BY ID ---
    public CatalogoResponseDTO obtenerPorId(Long id, String token) {
        authClient.validarToken(token);
        return repository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Set de Lego no encontrado con ID: " + id));
    }

    // --- UPDATE ---
    public CatalogoResponseDTO actualizar(Long id, CatalogoRequestDTO dto, String token) {
        AuthResponseDTO auth = authClient.validarToken(token);
        if (!"ADMIN".equals(auth.getRol())) {
            throw new RuntimeException("No tienes permisos para editar el catálogo.");
        }

        return repository.findById(id).map(lego -> {
            lego.setNombre(dto.getNombre());
            lego.setTema(dto.getTema());
            lego.setPiezas(dto.getPiezas());
            lego.setPrecio(dto.getPrecio());
            return mapToDTO(repository.save(lego));
        }).orElseThrow(() -> new RuntimeException("Lego no encontrado"));
    }

    // --- DELETE ---
    public void eliminar(Long id, String token) {
        AuthResponseDTO auth = authClient.validarToken(token);
        if (!"ADMIN".equals(auth.getRol())) {
            throw new RuntimeException("Solo el administrador puede eliminar productos.");
        }
        repository.deleteById(id);
    }

    // Mapeo usando el Builder de tu CatalogoResponseDTO
    private CatalogoResponseDTO mapToDTO(Lego lego) {
        return CatalogoResponseDTO.builder()
                .id(lego.getId())
                .nombre(lego.getNombre())
                .tema(lego.getTema())
                .piezas(lego.getPiezas())
                .precio(lego.getPrecio())
                .build();
    }
}
