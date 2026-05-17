package com.piezas_jj.piezas_jj.services;

import com.piezas_jj.piezas_jj.dto.*;
import com.piezas_jj.piezas_jj.exception.ResourceNotFoundException;
import com.piezas_jj.piezas_jj.model.Pieza;
import com.piezas_jj.piezas_jj.repository.PiezaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PiezaService {

    private final PiezaRepository repository;

    public List<PiezaResponseDTO> listarTodas() {
        return repository.findAll().stream()
                .map(p -> mapToDTO(p, "Lista cargada"))
                .collect(Collectors.toList());
    }

    public PiezaResponseDTO obtenerPorId(Long id) {
        Pieza pieza = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pieza no encontrada"));
        return mapToDTO(pieza, "Búsqueda exitosa");
    }

    public PiezaResponseDTO crear(PiezaRequestDTO dto) {
        Pieza nueva = Pieza.builder()
                .nombre(dto.getNombre()).categoria(dto.getCategoria())
                .color(dto.getColor()).stock(dto.getStock())
                .precio(dto.getPrecio()).build();
        return mapToDTO(repository.save(nueva), "Pieza creada");
    }

    public PiezaResponseDTO actualizar(Long id, PiezaRequestDTO dto) {
        Pieza p = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se pudo actualizar"));
        p.setNombre(dto.getNombre());
        p.setStock(dto.getStock());
        p.setPrecio(dto.getPrecio());
        return mapToDTO(repository.save(p), "Actualización exitosa");
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("ID no existe");
        repository.deleteById(id);
    }

    private PiezaResponseDTO mapToDTO(Pieza p, String msg) {
        return PiezaResponseDTO.builder()
                .id(p.getId()).nombre(p.getNombre()).categoria(p.getCategoria())
                .color(p.getColor()).stock(p.getStock()).precio(p.getPrecio())
                .mensaje(msg).build();
    }
}
