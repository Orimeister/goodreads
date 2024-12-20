package com.orimeister.review_service.repository;

import com.orimeister.review_service.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> getReviewsByBookId(Integer bookId);
}
