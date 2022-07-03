package com.snizhel.libraryManagement.service;




import com.snizhel.libraryManagement.model.Book;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface BookService {
  List< Book > findAll();
  List<Book> findByTitleAndAuthor(String title);

  Book searchByTitle(String title);

  Book findById(Integer id);

  Book save(Book book);

  void delete(Integer id);

  void update(Integer id, Book book);

  Boolean checkIfBookExist(String title, String author);

}
