package com.catalogo_jj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.catalogo_jj.model.Lego;

import java.util.Optional;

public interface CatalogoRepository extends JpaRepository<Lego, Long> {
    Optional<Lego> findByNombre(String nombre);
}
