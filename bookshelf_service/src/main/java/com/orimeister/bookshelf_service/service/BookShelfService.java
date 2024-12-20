package com.orimeister.bookshelf_service.service;

import com.orimeister.bookshelf_service.model.BookDTO;
import com.orimeister.bookshelf_service.model.BookShelf;
import com.orimeister.bookshelf_service.model.BookStatus;
import com.orimeister.bookshelf_service.repository.BookShelfRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BookShelfService {
    @Autowired
    private BookShelfRepository bookShelfRepository;
    @Autowired
    private RestTemplate restTemplate;
    private final String bookServiceUrl = "http://book-service/books/";
    private final String userServiceUrl = "http://localhost:8081/users/";

    public boolean isBookExist(Integer bookId){
        try{
            BookDTO book = restTemplate.getForObject(bookServiceUrl+bookId,BookDTO.class);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    public boolean isUserExist(Integer userId) {
        try {
            restTemplate.getForObject(userServiceUrl + userId, Object.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<BookShelf> finAllBookShelves(){
        return bookShelfRepository.findAll();
    }
    public BookShelf findBookShelf(Integer id){
        return bookShelfRepository.findById(id).orElse(null);
    }
    public BookShelf createBookShelf(Integer userId, Integer bookId, BookStatus bookStatus){
        if(!isBookExist(bookId)){
            throw new EntityNotFoundException("Book not found!"+bookId);

        }
        if (!isUserExist(userId)) {
            throw new EntityNotFoundException("User not found: " + userId);
        }
        else{
            BookShelf bookShelf = new BookShelf();
            bookShelf.setBookId(bookId);
            bookShelf.setBookStatus(bookStatus);
            bookShelf.setUserId(userId);
            return bookShelfRepository.save(bookShelf);
        }

    }
    public BookShelf updateBookShelf(Integer id, BookShelf bookShelfDetails){
        BookShelf bookShelf = bookShelfRepository.findById(id).orElseThrow(()->new RuntimeException("Book not found"));
        bookShelf.setUserId(bookShelfDetails.getUserId());
        bookShelf.setBookId(bookShelfDetails.getBookId());
        bookShelf.setBookStatus(bookShelfDetails.getBookStatus());
        return bookShelfRepository.save(bookShelf);
    }
    public boolean deleteBookShelf(Integer id)  {
        if(bookShelfRepository.existsById(id)){
            bookShelfRepository.deleteById(id);
            return true;
        } else{
            return false;
        }

    }
}
