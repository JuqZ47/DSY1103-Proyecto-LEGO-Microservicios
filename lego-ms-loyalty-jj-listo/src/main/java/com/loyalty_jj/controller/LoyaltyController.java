package com.loyalty_jj.controller;

import com.loyalty_jj.client.AuthClient;
import com.loyalty_jj.dto.LoyaltyDTO;
import com.loyalty_jj.model.Loyalty;
import com.loyalty_jj.services.LoyaltyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loyalty")
public class LoyaltyController {

    private final LoyaltyService loyaltyService;
    private final AuthClient authClient;

    public LoyaltyController(LoyaltyService loyaltyService, AuthClient authClient) {
        this.loyaltyService = loyaltyService;
        this.authClient = authClient;
    }

    @PostMapping
    public Loyalty crearLoyalty(
            @RequestHeader("Authorization") String token,
            @RequestBody LoyaltyDTO loyaltyDTO) {

        validar(token);
        return loyaltyService.crearLoyalty(loyaltyDTO);
    }

    @GetMapping
    public List<Loyalty> listarLoyalty(@RequestHeader("Authorization") String token) {
        validar(token);
        return loyaltyService.listarLoyalty();
    }

    @GetMapping("/{id}")
    public Loyalty obtenerPorId(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id) {

        validar(token);
        return loyaltyService.obtenerPorId(id);
    }

    @GetMapping("/usuario/{userId}")
    public Loyalty obtenerPorUserId(
            @RequestHeader("Authorization") String token,
            @PathVariable Long userId) {

        validar(token);
        return loyaltyService.obtenerPorUserId(userId);
    }

    @PutMapping("/{id}")
    public Loyalty actualizarLoyalty(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody LoyaltyDTO loyaltyDTO) {

        validar(token);
        return loyaltyService.actualizarLoyalty(id, loyaltyDTO);
    }

    @DeleteMapping("/{id}")
    public String eliminarLoyalty(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id) {

        validar(token);
        loyaltyService.eliminarLoyalty(id);
        return "Loyalty eliminado correctamente";
    }

    private void validar(String token) {
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Token requerido");
        }

        authClient.validarToken(token);
    }
}