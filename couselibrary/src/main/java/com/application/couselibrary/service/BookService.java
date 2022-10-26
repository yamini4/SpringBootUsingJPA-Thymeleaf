package com.application.couselibrary.service;

import com.application.couselibrary.entity.Book;
import com.application.couselibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;


    public List<Book>findAllBooks(){
        return bookRepository.findAll();
    }

    public Book findBookById(long id){
        Book book;
        book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book Not Found"));
        return book;
    }

    public void createBook(Book book){
        bookRepository.save(book);
    }

    public void deleteBook(Long id){
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book Not Found"));
        bookRepository.deleteById(book.getId());
    }
    public void updateBook(Book book){

        bookRepository.save(book);
    }
}









