package com.auth_jj.auth_jj.repository;

import java.util.Optional;
import com.auth_jj.auth_jj.model.Autenticacion;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthRepository extends JpaRepository<Autenticacion, Long> {
    Optional<Autenticacion> findByUsername(String username);
}
