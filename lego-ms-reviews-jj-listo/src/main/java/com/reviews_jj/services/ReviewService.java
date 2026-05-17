package com.reviews_jj.services;

import com.reviews_jj.dto.ReviewRequestDTO;
import com.reviews_jj.dto.ReviewResponseDTO;
import com.reviews_jj.exception.ResourceNotFoundException;
import com.reviews_jj.model.Review;
import com.reviews_jj.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public ReviewResponseDTO guardarReview(ReviewRequestDTO request) {
        Review review = new Review();

        review.setUsuarioId(request.getUsuarioId());
        review.setSetId(request.getSetId());
        review.setComentario(request.getComentario());
        review.setCalificacion(request.getCalificacion());
        review.setFecha(LocalDateTime.now());

        Review guardado = reviewRepository.save(review);

        return convertirAResponse(guardado);
    }

    public List<ReviewResponseDTO> listarReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    public ReviewResponseDTO obtenerReviewPorId(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review no encontrada con id: " + id));

        return convertirAResponse(review);
    }

    public List<ReviewResponseDTO> obtenerReviewsPorSet(Long setId) {
        return reviewRepository.findBySetId(setId)
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    public List<ReviewResponseDTO> obtenerReviewsPorUsuario(Long usuarioId) {
        return reviewRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    public ReviewResponseDTO actualizarReview(Long id, ReviewRequestDTO request) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review no encontrada con id: " + id));

        review.setUsuarioId(request.getUsuarioId());
        review.setSetId(request.getSetId());
        review.setComentario(request.getComentario());
        review.setCalificacion(request.getCalificacion());

        Review actualizado = reviewRepository.save(review);

        return convertirAResponse(actualizado);
    }

    public void eliminarReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review no encontrada con id: " + id));

        reviewRepository.delete(review);
    }

    private ReviewResponseDTO convertirAResponse(Review review) {
        return new ReviewResponseDTO(
                review.getId(),
                review.getUsuarioId(),
                review.getSetId(),
                review.getComentario(),
                review.getCalificacion(),
                review.getFecha()
        );
    }
}