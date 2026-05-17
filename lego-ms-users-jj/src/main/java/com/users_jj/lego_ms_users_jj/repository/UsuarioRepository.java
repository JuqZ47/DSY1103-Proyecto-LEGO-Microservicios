package com.users_jj.lego_ms_users_jj.repository;

import com.users_jj.lego_ms_users_jj.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}