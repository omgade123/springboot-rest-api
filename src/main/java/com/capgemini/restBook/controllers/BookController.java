package com.capgemini.restBook.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.restBook.entities.Book;
import com.capgemini.restBook.services.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

	private final BookService service;

	@Autowired
	public BookController(BookService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> books = service.getAllBooks();
		return ResponseEntity.status(HttpStatus.OK).body(books);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id) {
		Book book = service.getBookById(id);
		if (book != null) {
			return ResponseEntity.status(HttpStatus.OK).body(book);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PostMapping
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
		Book saved = service.createBook(book);
		return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/api/books/" + saved.getBookId()))
				.body(saved);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book newBook) {
		Book updated = service.updateBook(id, newBook);
		if (updated != null) {
			return ResponseEntity.status(HttpStatus.OK).body(updated);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Book> patchBook(@PathVariable Long id, @RequestBody Book patch) {
		Book updated = service.patchBook(id, patch);
		if (updated != null) {
			return ResponseEntity.status(HttpStatus.OK).body(updated);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
		Book deleted = service.deleteBook(id);
		if (deleted != null) {
			return ResponseEntity.status(HttpStatus.OK).body(deleted);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
