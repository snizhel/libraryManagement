package com.snizhel.libraryManagement.service;



import com.snizhel.libraryManagement.model.Customer;

import java.util.List;
import java.util.Optional;

public interface UserService {
  List< Customer > findAll();

  Optional<Customer> findUserByName( String name );

  Customer findById( Integer id);

  Customer save(Customer user);

  void delete(Integer id);

  void update(Integer id, Customer user);

  Customer login(String name, String password);
}
