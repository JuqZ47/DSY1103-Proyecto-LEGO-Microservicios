package com.reviews_jj.repository;

import com.reviews_jj.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findBySetId(Long setId);

    List<Review> findByUsuarioId(Long usuarioId);
}