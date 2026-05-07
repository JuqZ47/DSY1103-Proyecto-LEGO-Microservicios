package com.pago_jj.pago_jj.repository;

import com.pago_jj.pago_jj.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Long> {

    List<Pago> findByIdLegoReferencia(Long idLegoReferencia);
}
