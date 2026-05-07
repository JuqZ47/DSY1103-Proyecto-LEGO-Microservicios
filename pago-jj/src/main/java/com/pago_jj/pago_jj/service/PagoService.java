package com.pago_jj.pago_jj.service;

import com.pago_jj.pago_jj.catalogofeing.CatalogoClient;
import com.pago_jj.pago_jj.dto.PagoRequestDTO;
import com.pago_jj.pago_jj.dto.PagoResponseDTO;
import com.pago_jj.pago_jj.exception.ResourceNotFoundException;
import com.pago_jj.pago_jj.model.Pago;
import com.pago_jj.pago_jj.repository.PagoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PagoService {
    private final PagoRepository pagoRepository;
    private final CatalogoClient catalogoClient;

    //LISTAR
    public List<PagoResponseDTO> listarTodo() {
        return pagoRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    //GUARDAR
    public PagoResponseDTO guardar(PagoRequestDTO request) {
        log.info("Validando LEGO ID: {} mediante Feign antes de pagar", request.getIdLegoReferencia());

        try {
            // Comunicación síncrona con ms-catalogo
            catalogoClient.obtenerLegoPorId(request.getIdLegoReferencia());

            Pago pago = Pago.builder()
                    .idLegoReferencia(request.getIdLegoReferencia())
                    .monto(request.getMonto())
                    .metodoPago(request.getMetodoPago())
                    .fechaPago(LocalDateTime.now())
                    .build();

            return mapToResponse(pagoRepository.save(pago));
        } catch (Exception e) {
            throw new RuntimeException("Error: El producto no existe en el catálogo.");
        }
    }

    //ACTUALIZAR
    public PagoResponseDTO actualizar(Long id, PagoRequestDTO request) {
        Pago pagoExistente = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));

        try {
            // Validar que el nuevo ID de referencia sea válido
            catalogoClient.obtenerLegoPorId(request.getIdLegoReferencia());

            pagoExistente.setIdLegoReferencia(request.getIdLegoReferencia());
            pagoExistente.setMonto(request.getMonto());
            pagoExistente.setMetodoPago(request.getMetodoPago());

            return mapToResponse(pagoRepository.save(pagoExistente));
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar: Producto de referencia no válido.");
        }
    }

    //ELIMINAR
    public void eliminar(Long id) {
        if (!pagoRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar: El pago con ID " + id + " no existe.");
        }
        pagoRepository.deleteById(id);
    }


    private PagoResponseDTO mapToResponse(Pago pago) {
        return PagoResponseDTO.builder()
                .id(pago.getId())
                .idLegoReferencia(pago.getIdLegoReferencia())
                .monto(pago.getMonto())
                .metodoPago(pago.getMetodoPago())
                .fechaPago(pago.getFechaPago())
                .build();
    }
}
