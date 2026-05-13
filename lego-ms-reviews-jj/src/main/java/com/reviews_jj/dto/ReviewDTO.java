
package com.reviews_jj.dto;

public class ReviewDTO {

    private Long id;

    private Long setId;

    private Long userId;

    private Integer rating;

    private String comentario;

    private String fecha;

    public ReviewDTO() {
    }

    public ReviewDTO(Long id,
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