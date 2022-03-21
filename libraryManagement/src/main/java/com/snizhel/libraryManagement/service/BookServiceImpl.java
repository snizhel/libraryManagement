package com.snizhel.libraryManagement.service;


import com.snizhel.libraryManagement.model.Book;
import com.snizhel.libraryManagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {
  @Autowired private BookRepository bookRepository;

  @Override
  public List< Book > findAll() {
    List<Book> books = new ArrayList<>();
    bookRepository.findAll().forEach(books::add);
    return books;
  }

  @Override
  public List<Book> findByTitle(String title) {
    if (title == null || title.isEmpty()) {
      return findAll();
    }
    return bookRepository.findByTitle(title);
  }

  @Override
  public Book findById(Integer id) {
    return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
  }

  @Override
  public Book save(Book book) {
    return bookRepository.save(book);
  }

  @Override
  public void delete(Integer id) {
    bookRepository.deleteById(id);
  }

  @Override
  public void update(Integer id, Book book) {
    Book bookToUpdate =
        bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    bookToUpdate.setTitle(book.getTitle());
    bookToUpdate.setAuthor(book.getAuthor());
    bookToUpdate.setPublisher(book.getPublisher());
    bookToUpdate.setPrice(book.getPrice());
    bookToUpdate.setQuantity(book.getQuantity());
    bookRepository.save(bookToUpdate);
  }
}
