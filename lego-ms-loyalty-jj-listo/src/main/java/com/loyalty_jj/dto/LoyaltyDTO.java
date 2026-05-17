package com.loyalty_jj.dto;

public class LoyaltyDTO {
    private Long id;
    private Long userId;
    private Integer puntos;
    private String nivel;
    private Integer comprasRealizadas;

    public LoyaltyDTO() {
    }

    public LoyaltyDTO(Long id,
                      Long userId,
                      Integer puntos,
                      String nivel,
                      Integer comprasRealizadas) {

        this.id = id;
        this.userId = userId;
        this.puntos = puntos;
        this.nivel = nivel;
        this.comprasRealizadas = comprasRealizadas;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public String getNivel() {
        return nivel;
    }

    public Integer getComprasRealizadas() {
        return comprasRealizadas;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public void setComprasRealizadas(Integer comprasRealizadas) {
        this.comprasRealizadas = comprasRealizadas;
    }
}