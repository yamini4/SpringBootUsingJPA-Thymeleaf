package com.application.couselibrary;

import com.application.couselibrary.entity.Author;
import com.application.couselibrary.entity.Book;
import com.application.couselibrary.entity.Category;
import com.application.couselibrary.entity.Publisher;
import com.application.couselibrary.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CouselibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouselibraryApplication.class, args);
	}

	@Bean
	public CommandLineRunner initialCreate(BookService bookService){
		return(args) -> {
			Book book1 = new Book("ABC1","My First Book", "Book Name");
			Author author1 = new Author("Test Name1","Test Description");
			Category category1 = new Category("Business books");
			Publisher publisher1 = new Publisher("First Publisher");
			book1.addAuthor(author1);
			book1.addCategory(category1);
			book1.addPublisher(publisher1);
			bookService.createBook(book1);

			Book book2 = new Book("ABC2","My Second Book", "Book Name");
			Author author2 = new Author("Test Name2","Test Description");
			Category category2 = new Category("Science books");
			Publisher publisher2 = new Publisher("Second Publisher");
			book2.addAuthor(author2);
			book2.addCategory(category2);
			book2.addPublisher(publisher2);
			bookService.createBook(book2);

			Book book3 = new Book("ABC3","My Third Book", "Book Name");
			Author author3 = new Author("Test Name3","Test Description");
			Category category3 = new Category("Maths books");
			Publisher publisher3 = new Publisher("Third Publisher");
			book3.addAuthor(author3);
			book3.addCategory(category3);
			book3.addPublisher(publisher3);
			bookService.createBook(book3);
		};
	}
}
