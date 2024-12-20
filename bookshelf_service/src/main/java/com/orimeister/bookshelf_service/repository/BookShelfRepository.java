package com.orimeister.bookshelf_service.repository;

import com.orimeister.bookshelf_service.model.BookDTO;
import com.orimeister.bookshelf_service.model.BookShelf;
import com.orimeister.bookshelf_service.model.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookShelfRepository extends JpaRepository<BookShelf, Integer> {
    List<BookDTO> findBooksByBookStatus(BookStatus bookStatus);
}
