package com.snizhel.libraryManagement.service;


import com.snizhel.libraryManagement.model.Customer;
import com.snizhel.libraryManagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  private CustomerRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Customer user =
        userRepository
            .findByName(username)
            .orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with username: " + username));
    return UserDetailsImpl.build(user);
  }
}
