package com.orimeister.review_service.service;

import com.orimeister.review_service.model.Book;
import com.orimeister.review_service.model.Review;
import com.orimeister.review_service.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service

public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private RestTemplate restTemplate;

    private final String bookServiceUrl = "http://book-service/books/";

    public boolean isBookExist(Integer bookId){
        try{
            Book book = restTemplate.getForObject(bookServiceUrl+bookId,Book.class);
            return true;
        } catch(Exception e){
            return false;
        }
    }
    public Review createReview(Review review){
        if(!isBookExist(review.getBookId())){
            throw new EntityNotFoundException("Book not found!"+review.getBookId());
        }
    return reviewRepository.save(review);
    }

    public List<Review> getReviewForBook(Integer bookId){
        return reviewRepository.getReviewsByBookId(bookId);
    }
    public void deleteReview(Integer id){
        reviewRepository.deleteById(id);
    }


}
