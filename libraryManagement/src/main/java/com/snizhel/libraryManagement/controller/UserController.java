package com.snizhel.libraryManagement.controller;

import com.snizhel.libraryManagement.model.BorrowBook;
import com.snizhel.libraryManagement.model.User;

import com.snizhel.libraryManagement.payload.BorrowBookDto;
import com.snizhel.libraryManagement.repository.BorrowBookRepository;
import com.snizhel.libraryManagement.service.BookService;
import com.snizhel.libraryManagement.service.BorrowBookService;
import com.snizhel.libraryManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth/users")
@Secured("ADMIN")
public class UserController {
  @Autowired private BookService bookService;
  @Autowired private UserService userService;
  @Autowired private BorrowBookRepository borrowBookRepository;

  @Autowired private BorrowBookService borrowBookService;

  @GetMapping
  public ResponseEntity<List<User>> getAllUser() {
    List<User> users = userService.findAll();
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> editUser(
      @PathVariable("id") Integer id, @RequestBody User user) {
    userService.update(id, user);
    return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<User> deleteUser(@PathVariable("id") Integer id) {
    userService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/search/{name}")
  public ResponseEntity<List<User>> searchUser(@PathVariable("name") String name) {
    if (name.equals("") && name == null) {
      return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(userService.searchByName(name), HttpStatus.OK);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
    User user = userService.findById(id);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @PostMapping("/borrow")
  public ResponseEntity<?> createBorrowBook( @RequestBody BorrowBookDto borrowBookDto) {
    BorrowBook borrowBook = borrowBookService.createBorrowBook(borrowBookDto);
    return new ResponseEntity<>(borrowBook, HttpStatus.CREATED);
  }

  @GetMapping("/borrow")
  public ResponseEntity<List<BorrowBook>> getAllBorrowBook() {
    List<BorrowBook> borrowBooks = borrowBookService.getAllBorrowBook();
    return new ResponseEntity<>(borrowBooks, HttpStatus.OK);
  }

  @DeleteMapping("/borrow/{id}")
  public ResponseEntity<BorrowBook> deleteBorrowBook(@PathVariable("id") Integer id) {
    borrowBookService.deleteBorrowBook(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
