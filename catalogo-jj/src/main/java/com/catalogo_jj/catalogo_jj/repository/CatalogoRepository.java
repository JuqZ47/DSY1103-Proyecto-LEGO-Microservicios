package com.catalogo_jj.catalogo_jj.repository;


import com.catalogo_jj.catalogo_jj.model.Lego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatalogoRepository extends JpaRepository<Lego, Long> {
    Optional<Lego> findByNombre(String nombre);
    List<Lego> findByTema(String tema);

}
