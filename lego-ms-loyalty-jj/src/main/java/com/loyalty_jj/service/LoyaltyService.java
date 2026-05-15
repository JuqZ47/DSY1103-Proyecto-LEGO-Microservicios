package com.loyalty_jj.service;

import com.loyalty_jj.model.Loyalty;
import com.loyalty_jj.repository.LoyaltyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoyaltyService {

    private final LoyaltyRepository loyaltyRepository;

    public LoyaltyService(LoyaltyRepository loyaltyRepository) {
        this.loyaltyRepository = loyaltyRepository;
    }

    public List<Loyalty> obtenerLoyalty() {
        return loyaltyRepository.findAll();
    }

    public Loyalty obtenerLoyaltyPorId(Long id) {

        return loyaltyRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Loyalty no encontrado"));
    }

    public Loyalty obtenerPorUserId(Long userId) {

        return loyaltyRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado"));
    }

    public Loyalty guardarLoyalty(Loyalty loyalty) {

        if (loyalty.getPuntos() < 0) {
            throw new RuntimeException("Los puntos no pueden ser negativos");
        }

        return loyaltyRepository.save(loyalty);
    }

    public Loyalty actualizarLoyalty(Long id,
                                     Loyalty datosLoyalty) {

        Loyalty loyalty = obtenerLoyaltyPorId(id);

        loyalty.setUserId(datosLoyalty.getUserId());
        loyalty.setPuntos(datosLoyalty.getPuntos());
        loyalty.setNivel(datosLoyalty.getNivel());
        loyalty.setComprasRealizadas(datosLoyalty.getComprasRealizadas());

        return loyaltyRepository.save(loyalty);
    }

    public void eliminarLoyalty(Long id) {

        Loyalty loyalty = obtenerLoyaltyPorId(id);

        loyaltyRepository.delete(loyalty);
    }
}