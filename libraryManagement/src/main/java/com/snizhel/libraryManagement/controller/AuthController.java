package com.snizhel.libraryManagement.controller;

import com.snizhel.libraryManagement.payload.ChangPassRequest;
import com.snizhel.libraryManagement.payload.LoginRequest;
import com.snizhel.libraryManagement.payload.SignupRequest;
import com.snizhel.libraryManagement.response.JwtResponse;
import com.snizhel.libraryManagement.response.MessageResponse;
import com.snizhel.libraryManagement.security.AuthTokenFilter;
import com.snizhel.libraryManagement.security.JwtUtils;
import com.snizhel.libraryManagement.service.AuthUserService;
import com.snizhel.libraryManagement.service.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
  @Autowired AuthUserService authUserService;
  @Autowired JwtUtils jwtUtils;
  @Autowired AuthenticationManager authenticationManager;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    try {
      Authentication authentication =
          authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                  loginRequest.getUsername(), loginRequest.getPassword()));
      SecurityContextHolder.getContext().setAuthentication(authentication);
      UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
      List<String> roles =
          userDetails.getAuthorities().stream()
              .map(item -> item.getAuthority())
              .collect(Collectors.toList());
      ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
      String jwt = jwtUtils.generateJwtToken(authentication);
      authUserService.loginUser(loginRequest);
      return ResponseEntity.ok()
          .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
          .body(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles));
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return ResponseEntity.badRequest().body(new MessageResponse("Invalid username or password"));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (authUserService.isUserExist(signUpRequest.getUsername())) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }
    authUserService.registerUser(signUpRequest);
    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

  @PostMapping("/changePassword")
  public ResponseEntity<?> changePassword(@Valid @RequestBody ChangPassRequest changePassRequest) {
    try {
      authUserService.changePassword(changePassRequest);
      return ResponseEntity.ok(new MessageResponse("Password changed successfully!"));
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return ResponseEntity.badRequest().body(new MessageResponse("Error: Password not changed!"));
  }

  @Secured("ADMIN")
  @PutMapping("/ban/{id}")
  public ResponseEntity<?> blockUser(@PathVariable("id") Integer id) {
    authUserService.banUser(id);
    return ResponseEntity.ok(new MessageResponse("User blocked successfully!"));
  }

  @PostMapping("/logout")
  public ResponseEntity<?> logoutUser() {
    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
    return ResponseEntity.ok()
        .header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body(new MessageResponse("User logged out successfully!"));
  }

  @Secured("ADMIN")
  @PutMapping("/unban/{id}")
  public ResponseEntity<?> unblockUser(@PathVariable("id") Integer id) {
    authUserService.unbanUser(id);
    return ResponseEntity.ok(new MessageResponse("User unblocked successfully!"));
  }
}
