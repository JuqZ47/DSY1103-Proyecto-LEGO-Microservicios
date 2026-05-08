package com.pago_jj2.controller;

import com.pago_jj2.dto.PagoRequestDTO;
import com.pago_jj2.dto.PagoResponseDTO;
import com.pago_jj2.service.PagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class PagoController {
    private final PagoService pagoService;

    @GetMapping
    public ResponseEntity<List<PagoResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(pagoService.listarTodo());
    }

    @PostMapping
    public ResponseEntity<PagoResponseDTO> crear(@RequestBody PagoRequestDTO request) {
        return new ResponseEntity<>(pagoService.guardar(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoResponseDTO> editar(@PathVariable Long id, @RequestBody PagoRequestDTO request) {
        return ResponseEntity.ok(pagoService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pagoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
