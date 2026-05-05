package com.orders_jj.lego_ms_orders_jj.controller;

import com.orders_jj.lego_ms_orders_jj.dto.PedidoRequest;
import com.orders_jj.lego_ms_orders_jj.model.Pedido;
import com.orders_jj.lego_ms_orders_jj.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/orders")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<?> listarPedidos() {
        try {
            List<Pedido> pedidos = pedidoService.listarPedidos();
            return ResponseEntity.ok(pedidos);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "Error al listar pedidos");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPedido(@PathVariable Long id) {
        try {
            Pedido pedido = pedidoService.buscarPorId(id);

            if (pedido == null) {
                Map<String, String> error = new HashMap<>();
                error.put("mensaje", "Pedido no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }

            return ResponseEntity.ok(pedido);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "Error al buscar pedido");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarPedido(@Valid @RequestBody PedidoRequest request) {
        try {
            Pedido nuevoPedido = pedidoService.guardarPedido(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "Error interno al guardar pedido");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<?> actualizarEstado(
            @PathVariable Long id,
            @RequestParam String estado) {
        try {
            Pedido pedidoActualizado = pedidoService.actualizarEstado(id, estado);

            if (pedidoActualizado == null) {
                Map<String, String> error = new HashMap<>();
                error.put("mensaje", "Pedido no encontrado para actualizar");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }

            return ResponseEntity.ok(pedidoActualizado);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "Error al actualizar estado del pedido");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> listarPorUsuario(@PathVariable Long idUsuario) {
        try {
            return ResponseEntity.ok(pedidoService.listarPorUsuario(idUsuario));
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "Error al listar pedidos por usuario");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> listarPorEstado(@PathVariable String estado) {
        try {
            return ResponseEntity.ok(pedidoService.listarPorEstado(estado));
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "Error al listar pedidos por estado");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/set/{idSet}")
    public ResponseEntity<?> listarPorSet(@PathVariable Long idSet) {
        try {
            return ResponseEntity.ok(pedidoService.listarPorSet(idSet));
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "Error al listar pedidos por set");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPedido(@PathVariable Long id) {
        try {
            boolean eliminado = pedidoService.eliminarPedido(id);
            Map<String, String> respuesta = new HashMap<>();

            if (eliminado) {
                respuesta.put("mensaje", "Pedido eliminado correctamente");
                return ResponseEntity.ok(respuesta);
            }

            respuesta.put("mensaje", "Pedido no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "Error al eliminar pedido");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}