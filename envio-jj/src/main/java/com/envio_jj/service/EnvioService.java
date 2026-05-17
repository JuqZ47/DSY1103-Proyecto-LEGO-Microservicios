package com.envio_jj.service;
import com.envio_jj.client.AuthClient;
import com.envio_jj.client.PagoClient;
import com.envio_jj.dto.*;
import com.envio_jj.model.Envio;
import com.envio_jj.repository.EnvioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnvioService {

    private final EnvioRepository repository;
    private final AuthClient authClient;
    private final PagoClient pagoClient;

    // --- CREATE ---
    public EnvioResponseDTO registrarEnvio(EnvioRequestDTO dto, String token) {
        // 1. Validar Token con Auth-JJ
        authClient.validarToken(token);

        // 2. Validar que el Pago exista con Pago-JJ2
        try {
            pagoClient.obtenerPagoPorId(dto.getIdPago(), token);
        } catch (Exception e) {
            throw new RuntimeException("No se puede crear el envío: El pago con ID " + dto.getIdPago() + " no fue encontrado.");
        }

        Envio envio = new Envio();
        envio.setIdPago(dto.getIdPago());
        envio.setDireccionDestino(dto.getDireccionDestino());
        envio.setEstado("PREPARANDO"); // Estado inicial
        envio.setFechaDespacho(LocalDateTime.now());

        return mapToDTO(repository.save(envio));
    }

    // --- READ ALL ---
    public List<EnvioResponseDTO> obtenerTodos(String token) {
        AuthResponseDTO auth = authClient.validarToken(token);

        // Solo Admin o Logística ven todos los envíos
        if ("USER".equals(auth.getRol())) {
            throw new RuntimeException("Acceso denegado: No tienes permisos para ver todos los despachos.");
        }

        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // --- UPDATE ESTADO ---
    public EnvioResponseDTO actualizarEstado(Long id, String nuevoEstado, String token) {
        AuthResponseDTO auth = authClient.validarToken(token);

        if (!"ADMIN".equals(auth.getRol())) {
            throw new RuntimeException("Solo el administrador puede cambiar el estado de un envío.");
        }

        Envio envio = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Envío no encontrado"));

        envio.setEstado(nuevoEstado);
        return mapToDTO(repository.save(envio));
    }

    private EnvioResponseDTO mapToDTO(Envio envio) {
        return EnvioResponseDTO.builder()
                .id(envio.getId())
                .direccionDestino(envio.getDireccionDestino())
                .estado(envio.getEstado())
                .fechaDespacho(envio.getFechaDespacho())
                .build();
    }
}
