package com.catalogo_jj.catalogo_jj.service;

import com.catalogo_jj.catalogo_jj.dto.CatalogoRequestDTO;
import com.catalogo_jj.catalogo_jj.dto.CatalogoResponseDTO;
import com.catalogo_jj.catalogo_jj.model.Lego;
import com.catalogo_jj.catalogo_jj.repository.CatalogoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CatalogoService {
    private final CatalogoRepository catalogoRepository;

    //LISTAR
    public List<CatalogoResponseDTO> obtenerTodos() {
        log.info("Iniciando consulta de todos los productos del catálogo");
        return catalogoRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    //GUARDAR
    public CatalogoResponseDTO guardar(CatalogoRequestDTO request) {
        // IE 2.2.1: Regla de negocio para evitar duplicados
        if (catalogoRepository.findByNombre(request.getNombre()).isPresent()) {
            log.error("Error al guardar: El LEGO '{}' ya existe en la base de datos", request.getNombre());
            throw new RuntimeException("El producto ya existe en el catálogo");
        }

        log.info("Guardando nuevo LEGO: {}", request.getNombre());
        Lego lego = Lego.builder()
                .nombre(request.getNombre())
                .tema(request.getTema())
                .piezas(request.getPiezas())
                .precio(request.getPrecio())
                .build();

        return mapToResponse(catalogoRepository.save(lego));
    }

    //ACTUALIZAR
    public CatalogoResponseDTO actualizar(Long id, CatalogoRequestDTO request) {
        log.info("Intentando actualizar el registro con ID: {}", id);

        return catalogoRepository.findById(id)
                .map(legoExistente -> {
                    legoExistente.setNombre(request.getNombre());
                    legoExistente.setTema(request.getTema());
                    legoExistente.setPiezas(request.getPiezas());
                    legoExistente.setPrecio(request.getPrecio());

                    log.info("Producto con ID {} actualizado correctamente", id);
                    return mapToResponse(catalogoRepository.save(legoExistente));
                })
                .orElseThrow(() -> {
                    log.error("No se pudo actualizar: El ID {} no existe", id);
                    return new RuntimeException("No se encontró el LEGO con el ID proporcionado");
                });
    }

    //ELIMINAR
    public void eliminar(Long id) {
        log.warn("Solicitud para eliminar el registro ID: {}", id);
        if (!catalogoRepository.existsById(id)) {
            log.error("Fallo al eliminar: El ID {} no existe", id);
            throw new RuntimeException("No se puede eliminar: ID no encontrado");
        }
        catalogoRepository.deleteById(id);
        log.info("LEGO con ID {} eliminado exitosamente", id);
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
