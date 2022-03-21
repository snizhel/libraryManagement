package com.snizhel.libraryManagement.service;




import com.snizhel.libraryManagement.model.Book;

import java.util.List;

public interface BookService {
  List< Book > findAll();

  List<Book> findByTitle(String title);

  Book findById(Integer id);

  Book save(Book book);

  void delete(Integer id);

  void update(Integer id, Book book);
}
