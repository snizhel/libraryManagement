package com.snizhel.libraryManagement.service;


import com.snizhel.libraryManagement.model.Customer;
import com.snizhel.libraryManagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**/

@Service
@Transactional
public class UserServiceImpl implements UserService {
  @Autowired
  CustomerRepository userRepository;
  @Autowired PasswordEncoder encoder;

  @Override
  public List< Customer > findAll() {
    List<Customer> users = new ArrayList<>();
    userRepository.findAll().forEach(users::add);
    return users;
  }

  @Override
  public Optional<Customer> findUserByName(String name) {
    if (name == null || name.isEmpty()) {
      userRepository.findAll().forEach(System.out::println);
      throw new IllegalArgumentException("this name is not exist");
    }
    return userRepository.findByName(name);
  }

  @Override
  public Customer findById(Integer id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("id user not found"));
  }

  @Override
  public Customer save(Customer user) {
    return userRepository.save(user);
  }

  @Override
  public void delete(Integer id) {
    userRepository.deleteById(id);
  }

  @Override
  public void update(Integer id, Customer user) {
    Customer user1 =
        userRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
    user1.setName(user.getName());
    user1.setPassword(encoder.encode(user.getPassword()));
    user1.setBirthday(user.getBirthday());
    user1.setAddress(user.getAddress());
    user1.setPhone(user.getPhone());
    userRepository.save(user1);
  }

  @Override
  public Customer login(String name, String password) {
    Customer user = userRepository.findByNameAndPassword(name, password);
    if (user == null) {
      throw new IllegalArgumentException("login or password is incorrect");
    }
    return user;
  }
}
