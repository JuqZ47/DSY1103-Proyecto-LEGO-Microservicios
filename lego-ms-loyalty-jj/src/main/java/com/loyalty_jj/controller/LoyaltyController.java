package com.loyalty_jj.controller;

import com.loyalty_jj.model.Loyalty;
import com.loyalty_jj.service.LoyaltyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loyalty")
public class LoyaltyController {

    private final LoyaltyService loyaltyService;

    public LoyaltyController(LoyaltyService loyaltyService) {
        this.loyaltyService = loyaltyService;
    }

    @GetMapping
    public List<Loyalty> obtenerLoyalty() {
        return loyaltyService.obtenerLoyalty();
    }

    @GetMapping("/{id}")
    public Loyalty obtenerLoyaltyPorId(@PathVariable Long id) {
        return loyaltyService.obtenerLoyaltyPorId(id);
    }

    @GetMapping("/user/{userId}")
    public Loyalty obtenerPorUserId(@PathVariable Long userId) {
        return loyaltyService.obtenerPorUserId(userId);
    }

    @PostMapping
    public Loyalty guardarLoyalty(@RequestBody Loyalty loyalty) {
        return loyaltyService.guardarLoyalty(loyalty);
    }

    @PutMapping("/{id}")
    public Loyalty actualizarLoyalty(@PathVariable Long id,
                                     @RequestBody Loyalty loyalty) {

        return loyaltyService.actualizarLoyalty(id, loyalty);
    }

    @DeleteMapping("/{id}")
    public String eliminarLoyalty(@PathVariable Long id) {

        loyaltyService.eliminarLoyalty(id);

        return "Loyalty eliminado correctamente";
    }
}