package com.snizhel.libraryManagement.controller;


import com.snizhel.libraryManagement.model.Customer;
import com.snizhel.libraryManagement.model.ERole;
import com.snizhel.libraryManagement.model.Role;
import com.snizhel.libraryManagement.payload.LoginRequest;
import com.snizhel.libraryManagement.payload.SignupRequest;
import com.snizhel.libraryManagement.repository.CustomerRepository;
import com.snizhel.libraryManagement.repository.RoleRepository;
import com.snizhel.libraryManagement.response.JwtResponse;
import com.snizhel.libraryManagement.response.MessageResponse;
import com.snizhel.libraryManagement.security.JwtUtils;
import com.snizhel.libraryManagement.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired AuthenticationManager authenticationManager;
  @Autowired
  CustomerRepository userRepository;
  @Autowired
  RoleRepository roleRepository;
  @Autowired PasswordEncoder encoder;
  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles =
        userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());
    return ResponseEntity.ok(
        new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByName(signUpRequest.getUsername())) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    // Create new user's account
    Customer user =
        new Customer(
            signUpRequest.getUsername(),
            encoder.encode(signUpRequest.getPassword()),
            signUpRequest.getPhone(),
            signUpRequest.getAddress(),
            signUpRequest.getBirthday());
    Set<String> strRoles = signUpRequest.getRole();
    Set< Role > roles = new HashSet<>();
    if (strRoles == null) {
      Role userRole =
          roleRepository
              .findByName(ERole.USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(
          role -> {
            switch (role) {
              case "admin":
                Role adminRole =
                    roleRepository
                        .findByName(ERole.ADMIN)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(adminRole);
                break;

              default:
                Role userRole =
                    roleRepository
                        .findByName(ERole.USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            }
          });
    }
    user.setRoles(roles);
    userRepository.save(user);
    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

  @PostMapping("/signout")
  public ResponseEntity<?> logoutUser() {
    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
    return ResponseEntity.ok()
        .header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body(new MessageResponse("You've been signed out!"));
  }
}
