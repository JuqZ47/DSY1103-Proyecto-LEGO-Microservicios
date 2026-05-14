package com.pago_jj2.service;

import com.pago_jj2.catalogofeing.AuthClient;
import com.pago_jj2.catalogofeing.CatalogoClient;
import com.pago_jj2.dto.AuthResponseDTO;
import com.pago_jj2.dto.PagoRequestDTO;
import com.pago_jj2.dto.PagoResponseDTO;
import com.pago_jj2.model.Pago;
import com.pago_jj2.repository.PagoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PagoService {

    private final PagoRepository pagoRepository;
    private final AuthClient authClient;
    private final CatalogoClient catalogoClient;

    public PagoResponseDTO registrarPago(PagoRequestDTO dto, String token) {
        // Validar token
        authClient.validarToken(token);

        // Validar existencia del Lego en el catálogo
        Object lego = catalogoClient.obtenerLegoPorId(dto.getIdLegoReferencia());
        if (lego == null) {
            throw new RuntimeException("El set de Lego con ID " + dto.getIdLegoReferencia() + " no existe.");
        }

        Pago pago = new Pago();
        pago.setIdLegoReferencia(dto.getIdLegoReferencia());
        pago.setMonto(dto.getMonto());
        pago.setMetodoPago(dto.getMetodoPago());
        pago.setFechaPago(LocalDateTime.now());

        return mapToDTO(pagoRepository.save(pago));
    }

    public List<PagoResponseDTO> obtenerTodos(String token) {
        AuthResponseDTO auth = authClient.validarToken(token);

        if ("USER".equals(auth.getRol())) {
            throw new RuntimeException("Acceso denegatedo: No tienes permisos de administrador.");
        }

        return pagoRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public PagoResponseDTO obtenerPorId(Long id, String token) {
        authClient.validarToken(token);
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));
        return mapToDTO(pago);
    }

    public void eliminarPago(Long id, String token) {
        AuthResponseDTO auth = authClient.validarToken(token);

        if (!"ADMIN".equals(auth.getRol())) {
            throw new RuntimeException("Solo el administrador puede eliminar pagos.");
        }

        pagoRepository.deleteById(id);
    }

    private PagoResponseDTO mapToDTO(Pago pago) {
        return PagoResponseDTO.builder()
                .id(pago.getId())
                .idLegoReferencia(pago.getIdLegoReferencia())
                .monto(pago.getMonto())
                .metodoPago(pago.getMetodoPago())
                .fechaPago(pago.getFechaPago())
                .build();
    }
}
