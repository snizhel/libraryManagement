package com.snizhel.libraryManagement.service;

import com.snizhel.libraryManagement.model.User;
import com.snizhel.libraryManagement.model.ERole;
import com.snizhel.libraryManagement.model.EStatus;
import com.snizhel.libraryManagement.model.Role;
import com.snizhel.libraryManagement.payload.ChangPassRequest;
import com.snizhel.libraryManagement.payload.LoginRequest;
import com.snizhel.libraryManagement.payload.SignupRequest;
import com.snizhel.libraryManagement.repository.CustomerRepository;
import com.snizhel.libraryManagement.repository.RoleRepository;
import com.snizhel.libraryManagement.security.AuthTokenFilter;
import com.snizhel.libraryManagement.security.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthUserServiceImpl implements AuthUserService {
  private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
  @Autowired CustomerRepository userRepository;
  @Autowired PasswordEncoder encoder;
  @Autowired AuthenticationManager authenticationManager;
  @Autowired JwtUtils jwtUtils;
  @Autowired RoleRepository roleRepository;

  @Override
  public User loginUser(LoginRequest loginRequest) {
    return userRepository
        .findByName(loginRequest.getUsername())
        .orElseThrow(() -> new RuntimeException("User not found"));
  }

  @Override
  public User registerUser(SignupRequest signUpRequest) {
    User user =
        new User(
            signUpRequest.getUsername(),
            encoder.encode(signUpRequest.getPassword()),
            signUpRequest.getPhone(),
            signUpRequest.getAddress(),
            signUpRequest.getBirthday());
    user.setStatus(EStatus.AVAILABLE);
    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();
    for (String role : strRoles) {
      if (role.equals("ADMIN")) {
        Role adminRole =
            roleRepository
                .findByName(ERole.ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(adminRole);
      } else if (role.equals("USER") || role.isEmpty()) {
        Role userRole =
            roleRepository
                .findByName(ERole.USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
      }
    }
    user.setRoles(roles);
    return userRepository.save(user);
  }

  @Override
  public User changePassword(ChangPassRequest changePassRequest) {
    User user =
        userRepository
            .findById(changePassRequest.getId())
            .orElseThrow(() -> new RuntimeException("User not found"));
    if (!encoder.matches(changePassRequest.getOldPassword(), user.getPassword())) {
      throw new RuntimeException("Old password is not correct");
    }
    if (changePassRequest.getNewPassword().equals(changePassRequest.getOldPassword())) {
      throw new RuntimeException("New password is the same as old password");
    }
    if (encoder.matches(changePassRequest.getOldPassword(), user.getPassword())) {
      user.setPassword(encoder.encode(changePassRequest.getNewPassword()));
      return userRepository.save(user);
    }

    throw new RuntimeException("Wrong password");
  }

  @Override
  public User banUser(Integer id) {
    User user =
        userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    user.setStatus(EStatus.BANNED);
    userRepository.save(user);
    return user;
  }

  @Override
  public User unbanUser(Integer id) {
    User user =
        userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    user.setStatus(EStatus.AVAILABLE);
    userRepository.save(user);
    return user;
  }

  @Override
  public boolean isUserExist(String username) {
    return userRepository.existsByName(username);
  }
}
