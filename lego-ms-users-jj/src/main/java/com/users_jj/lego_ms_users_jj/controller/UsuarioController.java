package com.users_jj.lego_ms_users_jj.controller;

import com.users_jj.lego_ms_users_jj.dto.UsuarioRequest;
import com.users_jj.lego_ms_users_jj.model.Usuario;
import com.users_jj.lego_ms_users_jj.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarUsuario(@Valid @RequestBody UsuarioRequest request) {

        try {

            Usuario nuevoUsuario = usuarioService.guardarUsuario(request);

            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);

        } catch (Exception e) {

            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "Error al guardar usuario");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping
    public ResponseEntity<?> listarUsuarios() {

        try {

            return ResponseEntity.ok(usuarioService.listarUsuarios());

        } catch (Exception e) {

            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "Error al listar usuarios");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/correo/{correo}")
    public ResponseEntity<?> buscarPorCorreo(@PathVariable String correo) {

        try {

            Usuario usuario = usuarioService.buscarPorCorreo(correo);

            if (usuario == null) {

                Map<String, String> error = new HashMap<>();
                error.put("mensaje", "Usuario no encontrado");

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }

            return ResponseEntity.ok(usuario);

        } catch (Exception e) {

            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "Error al buscar usuario");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/rol/{rol}")
    public ResponseEntity<?> buscarPorRol(@PathVariable String rol) {

        try {

            return ResponseEntity.ok(usuarioService.buscarPorRol(rol));

        } catch (Exception e) {

            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "Error al buscar usuarios por rol");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}