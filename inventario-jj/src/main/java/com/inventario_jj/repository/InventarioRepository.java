package com.inventario_jj.repository;

import com.inventario_jj.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    Optional<Inventario> findByIdLegoReferencia(Long idLegoReferencia);
}
