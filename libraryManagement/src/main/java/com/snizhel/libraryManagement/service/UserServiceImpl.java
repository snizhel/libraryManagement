package com.snizhel.libraryManagement.service;

import com.snizhel.libraryManagement.model.User;
import com.snizhel.libraryManagement.repository.CustomerRepository;
import com.snizhel.libraryManagement.security.AuthTokenFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**/

@Service
public class UserServiceImpl implements UserService {
  private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
  @Autowired CustomerRepository userRepository;
  @Autowired PasswordEncoder encoder;

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }

  @Override
  public Optional<User> findUserByName(String name) {
    if (name == null || name.isEmpty()) {
      return Optional.empty();
    }
    return userRepository.findByName(name);
  }

  @Override
  public User findById(Integer id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("id user not found"));
  }

  @Override
  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  public void delete(Integer id) {
    userRepository.deleteById(id);
  }

  @Override
  public List<User> searchByName(String name) {
    if (name == null || name.isEmpty()) {
      return findAll();
    }
    return userRepository.findByNameContaining(name);
  }

  @Override
  public void update(Integer id, User user) {
    User userToUpdate =
        userRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
    userToUpdate.setName(user.getName());
    userToUpdate.setBirthday(user.getBirthday());
    userToUpdate.setAddress(user.getAddress());
    userToUpdate.setPhone(user.getPhone());
    userRepository.save(userToUpdate);
  }
}
