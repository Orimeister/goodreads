package com.orimeister.Book.Service.repository;

import com.orimeister.Book.Service.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    List<Book> findByUserId(Integer userId);


}
