package com.orimeister.Book.Service.service;

import com.orimeister.Book.Service.model.Book;
import com.orimeister.Book.Service.model.ReviewDTO;
import com.orimeister.Book.Service.model.UserDTO;
import com.orimeister.Book.Service.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private RestTemplate restTemplate;

    private final String userServiceUrl = "http://localhost:8081/users/";
    private final String  reviewServiceUrl ="http://review-service/reviews/";

    public Optional<Book> getBook(Integer id){
        return bookRepository.findById(id);
    }
    public List<Book> getBookByUserId(Integer userId){
        return bookRepository.findByUserId(userId);
    }
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public boolean isUserExist(Integer userId){
        try{
            restTemplate.getForObject(userServiceUrl+"/"+userId, Object.class);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    public Book createBook(Book book){
        if (!isUserExist(book.getUserId())){
            throw new IllegalArgumentException("User with the id of "+book.getUserId()+" is not found");
        } else{
            return bookRepository.save(book);
        }

    }
    public Book updateBook(Integer id, Book bookDetails){
        Book book = bookRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Book not found"+id));
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setDesc(bookDetails.getDesc());
        book.setGenre(bookDetails.getGenre());
        book.setPublishedDate(bookDetails.getPublishedDate());
        book.setUserId(bookDetails.getUserId());
        return bookRepository.save(book);
    }
    public void deleteBook(Integer id){
      if(bookRepository.existsById(id)){
          bookRepository.deleteById(id);
      } else {
          throw new EntityNotFoundException("Book not found with the id: "+id);
      }
    }
    public List<ReviewDTO> getReviewsForBook(Integer id){
        ReviewDTO[] reviewArrays = restTemplate.getForObject(reviewServiceUrl+"/book/"+id, ReviewDTO[].class);
        assert reviewArrays != null;
        return Arrays.asList(reviewArrays);
    }
    public UserDTO getUserById(Integer userId){
        return restTemplate.getForObject(userServiceUrl+userId,UserDTO.class);
    }

}
