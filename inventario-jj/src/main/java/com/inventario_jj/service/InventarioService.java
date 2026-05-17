package com.inventario_jj.service;

import com.inventario_jj.catalogofeing.AuthClient;
import com.inventario_jj.catalogofeing.CatalogoClient;
import com.inventario_jj.dto.AuthResponseDTO;
import com.inventario_jj.dto.InventarioRequestDTO;
import com.inventario_jj.dto.InventarioResponseDTO;
import com.inventario_jj.model.Inventario; // Tu entidad de base de datos
import com.inventario_jj.repository.InventarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventarioService {

    private final InventarioRepository repository;
    private final AuthClient authClient;
    private final CatalogoClient catalogoClient;

    // --- REGISTRAR O ACTUALIZAR STOCK ---
    public InventarioResponseDTO guardarStock(InventarioRequestDTO dto, String token) {
        AuthResponseDTO auth = authClient.validarToken(token);

        // Regla: Solo el personal de bodega o ADMIN gestiona el inventario físico
        if (!"ADMIN".equals(auth.getRol())) {
            throw new RuntimeException("Acceso denegado: No tienes permisos para gestionar bodega.");
        }

        // Validación Distribuidora: ¿El Lego existe en el catálogo?
        Object lego = catalogoClient.obtenerLegoPorId(dto.getIdLegoReferencia(), token);
        if (lego == null) {
            throw new RuntimeException("No se puede registrar: El ID de referencia " + dto.getIdLegoReferencia() + " no existe.");
        }

        // Buscamos si ya existe para actualizarlo o crear uno nuevo
        Inventario inventario = repository.findByIdLegoReferencia(dto.getIdLegoReferencia())
                .orElse(new Inventario());

        inventario.setIdLegoReferencia(dto.getIdLegoReferencia());
        inventario.setCantidad(dto.getCantidad());
        inventario.setUbicacion(dto.getUbicacion());

        return mapToDTO(repository.save(inventario));
    }

    // --- LISTAR TODO EL INVENTARIO ---
    public List<InventarioResponseDTO> obtenerTodos(String token) {
        authClient.validarToken(token);
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // --- BUSCAR STOCK ESPECÍFICO ---
    public InventarioResponseDTO obtenerPorLegoId(Long idLego, String token) {
        authClient.validarToken(token);
        return repository.findByIdLegoReferencia(idLego)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("No se encontró stock para este producto."));
    }

    // --- ELIMINAR REGISTRO ---
    public void eliminar(Long id, String token) {
        AuthResponseDTO auth = authClient.validarToken(token);
        if (!"ADMIN".equals(auth.getRol())) {
            throw new RuntimeException("Operación prohibida.");
        }
        repository.deleteById(id);
    }

    private InventarioResponseDTO mapToDTO(Inventario inv) {
        return InventarioResponseDTO.builder()
                .id(inv.getId())
                .idLegoReferencia(inv.getIdLegoReferencia())
                .cantidad(inv.getCantidad())
                .ubicacion(inv.getUbicacion())
                .build();
    }
}
