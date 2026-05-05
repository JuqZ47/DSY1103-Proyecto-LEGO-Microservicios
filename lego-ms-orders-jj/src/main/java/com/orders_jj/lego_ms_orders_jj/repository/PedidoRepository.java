package com.orders_jj.lego_ms_orders_jj.repository;

import com.orders_jj.lego_ms_orders_jj.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByIdUsuario(Long idUsuario);

    List<Pedido> findByEstado(String estado);

    @Query("SELECT p FROM Pedido p WHERE p.idSet = ?1")
    List<Pedido> buscarPedidosPorSet(Long idSet);
}