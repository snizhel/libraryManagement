package com.snizhel.libraryManagement.repository;


import com.snizhel.libraryManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface CustomerRepository extends JpaRepository<User, Integer> {

  Optional<User> findByName(String name);

  Boolean existsByName(String name);

  List<User> findByNameContaining(String name);
  Boolean existsByPassword(String password);

}
