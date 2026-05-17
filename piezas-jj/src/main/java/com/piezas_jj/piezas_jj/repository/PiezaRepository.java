package com.piezas_jj.piezas_jj.repository;

import com.piezas_jj.piezas_jj.model.Pieza;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PiezaRepository extends JpaRepository<Pieza, Long> {
    List<Pieza> findByColor(String color);
}
