package com.orimeister.bookshelf_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="bookshelves")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookShelf {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name="user_id", nullable = false)
    private Integer userId;
    @Column(name="book_id", nullable = false)
    private Integer bookId;
    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private BookStatus bookStatus;

}
