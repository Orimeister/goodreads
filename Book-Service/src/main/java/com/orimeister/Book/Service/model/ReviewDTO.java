package com.orimeister.Book.Service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private Integer id;
    private Integer userId;
    private Integer bookId;
    private Integer rating;
    private String reviewText;
}
