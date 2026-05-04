package com.inventario_jj.inventario_jj.repository;

import com.inventario_jj.inventario_jj.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    Optional<Inventario> findByIdLegoReferencia(Long idLegoReferencia);
}
