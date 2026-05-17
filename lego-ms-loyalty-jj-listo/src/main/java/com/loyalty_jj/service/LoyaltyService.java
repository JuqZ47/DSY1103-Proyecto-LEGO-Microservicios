package com.loyalty_jj.services;

import com.loyalty_jj.dto.LoyaltyDTO;
import com.loyalty_jj.exception.ResourceNotFoundException;
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

    public Loyalty crearLoyalty(LoyaltyDTO loyaltyDTO) {
        Loyalty loyalty = new Loyalty();

        loyalty.setUserId(loyaltyDTO.getUserId());
        loyalty.setPuntos(loyaltyDTO.getPuntos());
        loyalty.setNivel(loyaltyDTO.getNivel());
        loyalty.setComprasRealizadas(loyaltyDTO.getComprasRealizadas());

        return loyaltyRepository.save(loyalty);
    }

    public List<Loyalty> listarLoyalty() {
        return loyaltyRepository.findAll();
    }

    public Loyalty obtenerPorId(Long id) {
        return loyaltyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loyalty no encontrado con id: " + id));
    }

    public Loyalty obtenerPorUserId(Long userId) {
        return loyaltyRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Loyalty no encontrado para userId: " + userId));
    }

    public Loyalty actualizarLoyalty(Long id, LoyaltyDTO loyaltyDTO) {
        Loyalty loyalty = obtenerPorId(id);

        loyalty.setUserId(loyaltyDTO.getUserId());
        loyalty.setPuntos(loyaltyDTO.getPuntos());
        loyalty.setNivel(loyaltyDTO.getNivel());
        loyalty.setComprasRealizadas(loyaltyDTO.getComprasRealizadas());

        return loyaltyRepository.save(loyalty);
    }

    public void eliminarLoyalty(Long id) {
        Loyalty loyalty = obtenerPorId(id);
        loyaltyRepository.delete(loyalty);
    }
}