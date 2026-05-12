package com.users_jj.lego_ms_users_jj.repository;

import com.users_jj.lego_ms_users_jj.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByCorreo(String correo);

    @Query("SELECT u FROM Usuario u WHERE u.rol = ?1")
    java.util.List<Usuario> buscarPorRol(String rol);
}