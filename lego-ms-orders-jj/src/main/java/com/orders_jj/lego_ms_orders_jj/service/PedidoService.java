package com.orders_jj.lego_ms_orders_jj.service;

import com.orders_jj.lego_ms_orders_jj.dto.PedidoRequest;
import com.orders_jj.lego_ms_orders_jj.model.Pedido;
import com.orders_jj.lego_ms_orders_jj.repository.PedidoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class PedidoService {

    private static final Logger log = LoggerFactory.getLogger(PedidoService.class);

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> listarPedidos() {
        log.info("Listando todos los pedidos");
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        log.info("Buscando pedido con id: " + id);
        return pedidoRepository.findById(id).orElse(null);
    }

    public Pedido guardarPedido(PedidoRequest request) {
        log.info("Guardando nuevo pedido");

        Pedido pedido = new Pedido();
        pedido.setIdUsuario(request.getIdUsuario());
        pedido.setIdSet(request.getIdSet());
        pedido.setCantidad(request.getCantidad());
        pedido.setTotal(request.getTotal());
        pedido.setEstado("PENDIENTE");
        pedido.setFechaPedido(LocalDateTime.now());

        return pedidoRepository.save(pedido);
    }

    public Pedido actualizarEstado(Long id, String estado) {
        log.info("Actualizando estado del pedido con id: " + id);

        Pedido pedido = buscarPorId(id);

        if (pedido != null) {
            pedido.setEstado(estado);
            return pedidoRepository.save(pedido);
        }

        return null;
    }

    public List<Pedido> listarPorUsuario(Long idUsuario) {
        log.info("Listando pedidos del usuario: " + idUsuario);
        return pedidoRepository.findByIdUsuario(idUsuario);
    }

    public List<Pedido> listarPorEstado(String estado) {
        log.info("Listando pedidos por estado: " + estado);
        return pedidoRepository.findByEstado(estado);
    }

    public List<Pedido> listarPorSet(Long idSet) {
        log.info("Listando pedidos por set: " + idSet);
        return pedidoRepository.buscarPedidosPorSet(idSet);
    }

    public boolean eliminarPedido(Long id) {
        log.info("Eliminando pedido con id: " + id);

        Pedido pedido = buscarPorId(id);

        if (pedido != null) {
            pedidoRepository.delete(pedido);
            return true;
        }

        return false;
    }
}