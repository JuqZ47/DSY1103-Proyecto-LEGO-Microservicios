package com.reviews_jj.controller;

import com.reviews_jj.client.AuthClient;
import com.reviews_jj.dto.ReviewRequestDTO;
import com.reviews_jj.dto.ReviewResponseDTO;
import com.reviews_jj.services.ReviewService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final AuthClient authClient;

    public ReviewController(ReviewService reviewService, AuthClient authClient) {
        this.reviewService = reviewService;
        this.authClient = authClient;
    }

    @PostMapping
    public ReviewResponseDTO guardarReview(
            @RequestHeader("Authorization") String token,
            @Valid @RequestBody ReviewRequestDTO request) {

        validar(token);
        return reviewService.guardarReview(request);
    }

    @GetMapping
    public List<ReviewResponseDTO> listarReviews(@RequestHeader("Authorization") String token) {
        validar(token);
        return reviewService.listarReviews();
    }

    @GetMapping("/{id}")
    public ReviewResponseDTO obtenerReviewPorId(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id) {

        validar(token);
        return reviewService.obtenerReviewPorId(id);
    }

    @GetMapping("/set/{setId}")
    public List<ReviewResponseDTO> obtenerReviewsPorSet(
            @RequestHeader("Authorization") String token,
            @PathVariable Long setId) {

        validar(token);
        return reviewService.obtenerReviewsPorSet(setId);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<ReviewResponseDTO> obtenerReviewsPorUsuario(
            @RequestHeader("Authorization") String token,
            @PathVariable Long usuarioId) {

        validar(token);
        return reviewService.obtenerReviewsPorUsuario(usuarioId);
    }

    @PutMapping("/{id}")
    public ReviewResponseDTO actualizarReview(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @Valid @RequestBody ReviewRequestDTO request) {

        validar(token);
        return reviewService.actualizarReview(id, request);
    }

    @DeleteMapping("/{id}")
    public String eliminarReview(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id) {

        validar(token);
        reviewService.eliminarReview(id);
        return "Review eliminada correctamente";
    }

    private void validar(String token) {
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Token requerido");
        }

        authClient.validarToken(token);
    }
}