package com.snizhel.libraryManagement.repository;


import com.snizhel.libraryManagement.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
  Optional< Customer > findByName( String name);

  Customer findByNameAndPassword(String name, String password);

  Boolean existsByName(String name);

}
