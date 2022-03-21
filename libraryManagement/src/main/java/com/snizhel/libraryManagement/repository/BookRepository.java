package com.snizhel.libraryManagement.repository;


import com.snizhel.libraryManagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
  List< Book > findByTitle( String title);
}
