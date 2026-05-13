package com.inventario_jj.service;

import com.inventario_jj.catalogofeing.CatalogoClient;
import com.inventario_jj.dto.InventarioRequestDTO;
import com.inventario_jj.dto.InventarioResponseDTO;
import com.inventario_jj.model.Inventario;
import com.inventario_jj.repository.InventarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventarioService {
    private final InventarioRepository inventarioRepository;
    private final CatalogoClient catalogoClient; // Cliente Feign para hablar con Catálogo


    public InventarioResponseDTO guardarStock(InventarioRequestDTO request) {
        log.info("Iniciando registro de stock para LEGO ID: {}", request.getIdLegoReferencia());

        //Validar que el LEGO exista en el otro microservicio
        validarExistenciaLego(request.getIdLegoReferencia());

        Inventario stock = Inventario.builder()
                .idLegoReferencia(request.getIdLegoReferencia())
                .cantidad(request.getCantidad())
                .ubicacion(request.getUbicacion())
                .build();

        return mapToResponse(inventarioRepository.save(stock));
    }


    public List<InventarioResponseDTO> obtenerTodo() {
        log.info("Consultando lista completa de inventario");
        return inventarioRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }


    public InventarioResponseDTO actualizar(Long id, InventarioRequestDTO request) {
        log.info("Actualizando stock con ID: {}", id);

        return inventarioRepository.findById(id)
                .map(stockExistente -> {
                    // Si el usuario cambia el ID de referencia, volvemos a validar
                    if (!stockExistente.getIdLegoReferencia().equals(request.getIdLegoReferencia())) {
                        validarExistenciaLego(request.getIdLegoReferencia());
                    }

                    stockExistente.setIdLegoReferencia(request.getIdLegoReferencia());
                    stockExistente.setCantidad(request.getCantidad());
                    stockExistente.setUbicacion(request.getUbicacion());

                    return mapToResponse(inventarioRepository.save(stockExistente));
                })
                .orElseThrow(() -> new RuntimeException("No se encontró el registro de stock con ID: " + id));
    }


    public void eliminar(Long id) {
        log.warn("Eliminando registro de inventario ID: {}", id);
        if (!inventarioRepository.existsById(id)) {
            throw new RuntimeException("Error: Registro de inventario no encontrado.");
        }
        inventarioRepository.deleteById(id);
    }


    private void validarExistenciaLego(Long idLego) {
        try {
            log.info("Llamando a ms-catalogo para validar ID: {}", idLego);
            catalogoClient.obtenerLegoPorId(idLego);
        } catch (Exception e) {
            log.error("Validación fallida: El LEGO con ID {} no existe en el catálogo", idLego);
            throw new RuntimeException("No se puede gestionar el stock: El producto no existe en el catálogo.");
        }
    }


    private InventarioResponseDTO mapToResponse(Inventario stock) {
        return InventarioResponseDTO.builder()
                .id(stock.getId())
                .idLegoReferencia(stock.getIdLegoReferencia())
                .cantidad(stock.getCantidad())
                .ubicacion(stock.getUbicacion())
                .build();
    }
}
