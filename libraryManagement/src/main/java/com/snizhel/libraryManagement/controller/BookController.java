package com.snizhel.libraryManagement.controller;

import com.snizhel.libraryManagement.model.Book;
import com.snizhel.libraryManagement.response.MessageResponse;
import com.snizhel.libraryManagement.security.AuthTokenFilter;
import com.snizhel.libraryManagement.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth/books")
public class BookController {
  @Autowired private BookService bookService;
  private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

  @GetMapping
  public ResponseEntity<List<Book>> getAllBooks() {
    List<Book> books = bookService.findAll();
    return new ResponseEntity<>(books, HttpStatus.OK);
  }

  @Secured("ADMIN")
  @PostMapping
  public ResponseEntity<?> addBook(@RequestBody @Valid Book book) {
    if (bookService.checkIfBookExist(book.getTitle(), book.getAuthor())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Book already exist"));
    }
    Book bookNew = bookService.save(book);
    return new ResponseEntity<>(bookNew, HttpStatus.CREATED);
  }

  @Secured("ADMIN")
  @PutMapping("/{id}")
  public ResponseEntity<?> editBook(@PathVariable("id") Integer id, @Valid @RequestBody Book book) {
    bookService.update(id, book);
    return new ResponseEntity<>(bookService.findById(id), HttpStatus.OK);
  }

  @Secured("ADMIN")
  @DeleteMapping("/{id}")
  public ResponseEntity<Book> deleteBook(@PathVariable("id") Integer id) {
    bookService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/search/{title}")
  public ResponseEntity<List<Book>> searchByTitle(
      @PathVariable(value = "title", required = false) String title) {
    List<Book> books = bookService.findByTitleAndAuthor(title);
    return ResponseEntity.ok(books);
  }

  @Secured("ADMIN")
  @GetMapping("/{id}")
  public ResponseEntity<Book> getBook(@PathVariable("id") Integer id) {
    Book book = bookService.findById(id);
    return ResponseEntity.ok(book);
  }
}
