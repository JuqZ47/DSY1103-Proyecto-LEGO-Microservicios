package com.reviews_jj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "REVIEWS")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long setId;

    private Long userId;

    private Integer rating;

    private String comentario;

    private String fecha;

    public Review() {
    }

    public Review(Long id,
                  Long setId,
                  Long userId,
                  Integer rating,
                  String comentario,
                  String fecha) {

        this.id = id;
        this.setId = setId;
        this.userId = userId;
        this.rating = rating;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public Long getSetId() {
        return setId;
    }

    public Long getUserId() {
        return userId;
    }

    public Integer getRating() {
        return rating;
    }

    public String getComentario() {
        return comentario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSetId(Long setId) {
        this.setId = setId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}