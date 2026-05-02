package com.catalogo_jj.catalogo_jj.service;

import com.catalogo_jj.catalogo_jj.dto.CatalogoResponseDTO;
import com.catalogo_jj.catalogo_jj.dto.CatalogoRequestDTO;
import com.catalogo_jj.catalogo_jj.model.Lego;
import com.catalogo_jj.catalogo_jj.repository.CatalogoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogoService {
    private final CatalogoRepository catalogoRepository;

    public List<CatalogoResponseDTO> obtenerTodos() {
        return catalogoRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public Optional<CatalogoResponseDTO> obtenerPorId(Long id) {
        return catalogoRepository.findById(id)
                .map(this::mapToResponse);
    }

    public CatalogoResponseDTO guardar(CatalogoRequestDTO request) {
        if (catalogoRepository.findByNombre(request.getNombre()).isPresent()) {
            throw new RuntimeException("El producto ya existe en el catálogo");
        }

        Lego lego = Lego.builder()
                .nombre(request.getNombre())
                .tema(request.getTema())
                .piezas(request.getPiezas())
                .precio(request.getPrecio())
                .build();

        return mapToResponse(catalogoRepository.save(lego));
    }

    public void eliminar(Long id) {
        catalogoRepository.deleteById(id);
    }

    private CatalogoResponseDTO mapToResponse(Lego lego) {
        return CatalogoResponseDTO.builder()
                .id(lego.getId())
                .nombre(lego.getNombre())
                .tema(lego.getTema())
                .piezas(lego.getPiezas())
                .precio(lego.getPrecio())
                .build();
    }
}
