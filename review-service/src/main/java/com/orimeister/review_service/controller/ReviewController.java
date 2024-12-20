package com.orimeister.review_service.controller;

import com.orimeister.review_service.model.Review;
import com.orimeister.review_service.repository.ReviewRepository;
import com.orimeister.review_service.service.ReviewService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{bookId}")
    public List<Review> fetchReviewsForBoo(@PathVariable Integer bookId){
        return reviewService.getReviewForBook(bookId);
    }

    @PostMapping
    public Review createReview(@RequestBody Review review){
        return reviewService.createReview(review);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Integer id){
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

}
