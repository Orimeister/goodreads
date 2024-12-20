package com.orimeister.bookshelf_service.controller;

import com.orimeister.bookshelf_service.model.BookShelf;
import com.orimeister.bookshelf_service.model.BookStatus;
import com.orimeister.bookshelf_service.service.BookShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookshelf")
public class BookShelfController {
    @Autowired
    private BookShelfService bookShelfService;

    @GetMapping
    public ResponseEntity<List<BookShelf>> fetchAllShelves(){
        List<BookShelf> shelves = bookShelfService.finAllBookShelves();
        return ResponseEntity.ok(shelves);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookShelf> fetchBookShelf(@PathVariable Integer id){
        return ResponseEntity.ok(bookShelfService.findBookShelf(id));
    }
    @PostMapping
    public ResponseEntity<BookShelf> addBookToShelf(@RequestBody BookShelf bookShelf){
        BookShelf shelf = bookShelfService.createBookShelf(bookShelf.getUserId(), bookShelf.getBookId(), bookShelf.getBookStatus());
        return ResponseEntity.ok(shelf);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookShelf> updateShelf(@PathVariable Integer id, @RequestBody BookShelf bookShelfDetails) {
        return ResponseEntity.ok(bookShelfService.updateBookShelf(id, bookShelfDetails));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteShelf(@PathVariable Integer id){
        bookShelfService.deleteBookShelf(id);
        return ResponseEntity.noContent().build();
    }

}

