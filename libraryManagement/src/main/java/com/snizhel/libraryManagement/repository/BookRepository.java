package com.snizhel.libraryManagement.repository;

import com.snizhel.libraryManagement.model.Book;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
  Book findBookByTitle(String title);

  List<Book> findByTitleContaining(String title);

  Boolean existsByTitleAndAuthor(String title, String author);
}
