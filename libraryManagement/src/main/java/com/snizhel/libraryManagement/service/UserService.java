package com.snizhel.libraryManagement.service;

import com.snizhel.libraryManagement.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
  List<User> findAll();

  Optional<User> findUserByName(String name);

  User findById(Integer id);

  User save(User user);

  void delete(Integer id);

  List<User> searchByName(String name);

  void update(Integer id, User user);



}
