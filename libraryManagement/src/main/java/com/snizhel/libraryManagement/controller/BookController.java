package com.snizhel.libraryManagement.controller;


import com.snizhel.libraryManagement.model.Book;
import com.snizhel.libraryManagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/books")
//@PreAuthorize("hasRole('USER')")
public class BookController {
  @Autowired private BookService bookService;

  @GetMapping
  public ResponseEntity<List< Book >> getAllBooks() {
    List<Book> books = bookService.findAll();
    return new ResponseEntity<>(books, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Book> addBook(@RequestBody Book book) {
    Book book1 = bookService.save(book);
    HttpHeaders headers = new HttpHeaders();
    headers.add("Location", "/books/" + book1.getId().toString());
    return new ResponseEntity<>(book1, headers, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public String editBook(@PathVariable("id") Integer id, @RequestBody Book book) {
    bookService.update(id, book);
    return new ResponseEntity<>(bookService.findById(id), HttpStatus.OK).toString();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Book> deleteBook(@PathVariable("id") Integer id) {
    bookService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/search/{title}")
  public ResponseEntity<List<Book>> searchByTitle(@PathVariable("title") String title) {
    List<Book> books = bookService.findByTitle(title);
    return ResponseEntity.ok(books);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Book> getBook(@PathVariable("id") Integer id) {
    Book book = bookService.findById(id);
    return ResponseEntity.ok(book);
  }
}
