package com.orimeister.Book.Service.controller;

import com.orimeister.Book.Service.model.Book;
import com.orimeister.Book.Service.model.ReviewDTO;
import com.orimeister.Book.Service.model.UserDTO;
import com.orimeister.Book.Service.service.BookService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> fetchAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> fetchBookById(@PathVariable("id") Integer id){
        Optional<Book> book = bookService.getBook(id);
        return book.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.status(404).body(null));
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Book>> fetchBooksByUserId(@PathVariable("userId") Integer userId){
        List<Book> books = bookService.getBookByUserId(userId);
        return ResponseEntity.ok(books);
    }
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id, @RequestBody Book bookDetails){
        Book updateBooks=bookService.updateBook(id, bookDetails);
        return ResponseEntity.ok(updateBooks);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}/user")
    public ResponseEntity<UserDTO> fetchUserForBook(@PathVariable("id") Integer bookId){
        Optional<Book> book = bookService.getBook(bookId);
        if (book.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        UserDTO user = bookService.getUserById(book.get().getUserId());
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}/reviews")
    public List<ReviewDTO> getReviewsForBook(@PathVariable(value = "id") Integer bookId) {
        return bookService.getReviewsForBook(bookId);
    }

}
