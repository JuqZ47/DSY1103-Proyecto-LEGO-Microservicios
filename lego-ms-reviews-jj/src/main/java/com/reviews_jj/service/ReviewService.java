package com.reviews_jj.service;

import com.reviews_jj.model.Review;
import com.reviews_jj.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> obtenerReviews() {
        return reviewRepository.findAll();
    }

    public Review obtenerReviewPorId(Long id) {

        return reviewRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Review no encontrada"));
    }

    public List<Review> obtenerReviewsPorSet(Long setId) {
        return reviewRepository.findBySetId(setId);
    }

    public List<Review> obtenerReviewsPorUsuario(Long userId) {
        return reviewRepository.findByUserId(userId);
    }

    public Review guardarReview(Review review) {

        if (review.getRating() < 1 || review.getRating() > 5) {
            throw new RuntimeException("El rating debe ser entre 1 y 5");
        }

        review.setFecha(LocalDate.now().toString());

        return reviewRepository.save(review);
    }

    public Review actualizarReview(Long id, Review datosReview) {

        Review review = obtenerReviewPorId(id);

        review.setSetId(datosReview.getSetId());
        review.setUserId(datosReview.getUserId());
        review.setRating(datosReview.getRating());
        review.setComentario(datosReview.getComentario());

        return reviewRepository.save(review);
    }

    public void eliminarReview(Long id) {

        Review review = obtenerReviewPorId(id);

        reviewRepository.delete(review);
    }
}