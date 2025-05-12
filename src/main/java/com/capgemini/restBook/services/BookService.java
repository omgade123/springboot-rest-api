package com.capgemini.restBook.services;

import java.util.List;

import com.capgemini.restBook.entities.Book;

public interface BookService {

	List<Book> getAllBooks();

	Book getBookById(Long id);

	Book createBook(Book book);

	Book updateBook(Long id, Book book);

	Book patchBook(Long id, Book book);

	Book deleteBook(Long id);

}
