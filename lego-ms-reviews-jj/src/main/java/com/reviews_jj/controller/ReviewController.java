package com.reviews_jj.controller;

import com.reviews_jj.model.Review;
import com.reviews_jj.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<Review> obtenerReviews() {
        return reviewService.obtenerReviews();
    }

    @GetMapping("/{id}")
    public Review obtenerReviewPorId(@PathVariable Long id) {
        return reviewService.obtenerReviewPorId(id);
    }

    @GetMapping("/set/{setId}")
    public List<Review> obtenerReviewsPorSet(@PathVariable Long setId) {
        return reviewService.obtenerReviewsPorSet(setId);
    }

    @GetMapping("/usuario/{userId}")
    public List<Review> obtenerReviewsPorUsuario(@PathVariable Long userId) {
        return reviewService.obtenerReviewsPorUsuario(userId);
    }

    @PostMapping
    public Review guardarReview(@RequestBody Review review) {
        return reviewService.guardarReview(review);
    }

    @PutMapping("/{id}")
    public Review actualizarReview(@PathVariable Long id, @RequestBody Review review) {
        return reviewService.actualizarReview(id, review);
    }

    @DeleteMapping("/{id}")
    public String eliminarReview(@PathVariable Long id) {
        reviewService.eliminarReview(id);
        return "Review eliminada correctamente";
    }
}