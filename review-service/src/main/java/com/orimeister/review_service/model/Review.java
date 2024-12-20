package com.orimeister.review_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name="user_id", nullable = false)
    private Integer userId;
    @Column(name="book_id",nullable = false)
    private Integer bookId;
    @Column(name="rating",nullable = false)
    private Integer rating;
    @Column(name="review_text")
    private String reviewText;


}
