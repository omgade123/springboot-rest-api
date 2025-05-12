package com.capgemini.restBook.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.restBook.entities.Book;
import com.capgemini.restBook.repository.BookRepo;

@Service
public class BookServiceImpl implements BookService {

	private final BookRepo repository;

	@Autowired
	public BookServiceImpl(BookRepo repository) {
		this.repository = repository;
	}

	@Override
	public List<Book> getAllBooks() {
		return repository.findAll();
	}

	@Override
	public Book getBookById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Book createBook(Book book) {
		return repository.save(book);
	}

	@Override
	public Book updateBook(Long id, Book updated) {
		Optional<Book> optional = repository.findById(id);
		if (optional.isPresent()) {
			Book existing = optional.get();
			existing.setBookTitle(updated.getBookTitle());
			existing.setBookAuthor(updated.getBookAuthor());
			existing.setBookPrice(updated.getBookPrice());
			return repository.save(existing);
		}
		return null;

	}

	@Override
	public Book patchBook(Long id, Book patch) {
		Optional<Book> optional = repository.findById(id);
		if (optional.isPresent()) {
			Book existing = optional.get();
			if (patch.getBookTitle() != null) {
				existing.setBookTitle(patch.getBookTitle());
			}

			if (patch.getBookAuthor() != null) {
				existing.setBookAuthor(patch.getBookAuthor());
			}

			if (patch.getBookPrice() != null) {
				existing.setBookPrice(patch.getBookPrice());
			}
			 return repository.save(existing);
		}
		return null;
	}

	@Override
	public Book deleteBook(Long id) {
		Book b = null;
		if (repository.existsById(id)) {
			b = repository.findById(id).orElse(null);
			repository.deleteById(id);
			return b;
		}
		return null;
	}

}
