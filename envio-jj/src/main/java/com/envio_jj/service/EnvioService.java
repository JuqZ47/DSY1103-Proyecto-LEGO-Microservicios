package com.envio_jj.service;
import com.envio_jj.dto.EnvioRequestDTO;
import com.envio_jj.dto.EnvioResponseDTO;
import com.envio_jj.model.Envio;
import com.envio_jj.repository.EnvioRepository;
import com.envio_jj.client.PagoClient;
import com.envio_jj.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnvioService {
    private final EnvioRepository repository;
    private final PagoClient pagoClient;

    //LISTAR TODOS (Convertidos a DTO)
    public List<EnvioResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::convertirAEntidadDTO)
                .collect(Collectors.toList());
    }

    //REGISTRAR (Validando con Feign)
    public EnvioResponseDTO registrarEnvio(EnvioRequestDTO request) {
        // Validamos si el pago existe en el microservicio pago-jj
        Object pago = pagoClient.obtenerPagoPorId(request.getIdPago());

        if (pago == null) {
            throw new ResourceNotFoundException("No se puede generar el envío: Pago ID " + request.getIdPago() + " no encontrado.");
        }

        Envio envio = new Envio();
        envio.setIdPago(request.getIdPago());
        envio.setDireccionDestino(request.getDireccionDestino());
        envio.setEstado("PREPARANDO");
        envio.setFechaDespacho(LocalDateTime.now());

        Envio guardado = repository.save(envio);
        return convertirAEntidadDTO(guardado);
    }

    //ELIMINAR
    public void eliminarEnvio(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Envío no encontrado con ID: " + id);
        }
        repository.deleteById(id);
    }

    // ACTUALIZAR
    public EnvioResponseDTO actualizarEnvio(Long id, EnvioRequestDTO request) {
        return repository.findById(id).map(envio -> {
            // Actualizamos solo los campos necesarios
            envio.setDireccionDestino(request.getDireccionDestino());
            // Aquí podrías agregar lógica para actualizar el estado si lo sumas al DTO
            Envio actualizado = repository.save(envio);
            return convertirAEntidadDTO(actualizado);
        }).orElseThrow(() -> new ResourceNotFoundException("No se puede actualizar: Envío con ID " + id + " no existe."));
    }


    private EnvioResponseDTO convertirAEntidadDTO(Envio envio) {
        return EnvioResponseDTO.builder()
                .id(envio.getId())
                .direccionDestino(envio.getDireccionDestino())
                .estado(envio.getEstado())
                .fechaDespacho(envio.getFechaDespacho())
                .build();
    }
}
