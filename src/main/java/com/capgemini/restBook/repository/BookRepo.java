package com.capgemini.restBook.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.restBook.entities.Book;

public interface BookRepo extends JpaRepository<Book, Long> {
     
}
