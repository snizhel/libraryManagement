package com.snizhel.libraryManagement.controller;


import com.snizhel.libraryManagement.model.Customer;
import com.snizhel.libraryManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/users")
//@PreAuthorize("hasRole('USER')")
public class UserController {

  @Autowired private UserService userService;

  @GetMapping
  public ResponseEntity<List< Customer >> getAllUser() {
    List<Customer> users = userService.findAll();
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Customer> editUser(
      @PathVariable("id") Integer id, @RequestBody Customer user) {
    userService.update(id, user);
    return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Customer> deleteUser(@PathVariable("id") Integer id) {
    userService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  //  @GetMapping("/search/{name}")
  //  public ResponseEntity<List<Customer>> searchUser(@PathVariable("name") String name) {
  //    if (name.equals("")) {
  //      return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
  //    } else {
  //      return new ResponseEntity<  >(userService.findUserByName(name), HttpStatus.OK);
  //    }
  //  }

  @GetMapping("/{id}")
  public ResponseEntity<Customer> getUserById(@PathVariable("id") Integer id) {
    Customer user = userService.findById(id);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }
}
