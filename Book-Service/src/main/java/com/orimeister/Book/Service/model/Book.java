package com.orimeister.Book.Service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name ="title")
    private String title;
    @Column(name="author")
    private String author;
    @Column(name="description")
    private String desc;
    @Column(name="genre")
    private String genre;
    @Column(name="published_date")
    private Date publishedDate;
    @Column(name="user_id")
    private Integer userId;

}
