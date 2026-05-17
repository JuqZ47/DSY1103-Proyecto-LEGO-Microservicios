package com.loyalty_jj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "LOYALTY")
public class Loyalty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Integer puntos;

    private String nivel;

    private Integer comprasRealizadas;

    public Loyalty() {
    }

    public Loyalty(Long id,
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