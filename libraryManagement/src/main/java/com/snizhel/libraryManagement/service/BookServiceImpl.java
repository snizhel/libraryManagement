package com.snizhel.libraryManagement.service;

import com.snizhel.libraryManagement.model.Book;
import com.snizhel.libraryManagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {
  @Autowired private BookRepository bookRepository;

  @Override
  public List<Book> findAll() {
    return bookRepository.findAll();
  }

  @Override
  public List<Book> findByTitleAndAuthor(String title) {
    if (title == null) {
      return findAll();
    }
    return bookRepository.findByTitleContaining(title);
  }

  @Override
  public Book searchByTitle(String title) {
    return bookRepository.findBookByTitle(title);
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
    bookToUpdate.setQuantity(book.getQuantity());
    bookRepository.save(bookToUpdate);
  }

  @Override
  public Boolean checkIfBookExist(String title, String author) {
    return bookRepository.existsByTitleAndAuthor(title, author);
  }
}
