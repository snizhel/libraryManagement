package com.snizhel.libraryManagement.service;

import com.snizhel.libraryManagement.model.User;
import com.snizhel.libraryManagement.payload.ChangPassRequest;
import com.snizhel.libraryManagement.payload.LoginRequest;
import com.snizhel.libraryManagement.payload.SignupRequest;

public interface AuthUserService {
  User loginUser(LoginRequest loginRequest);

  User registerUser(SignupRequest signUpRequest);

  User changePassword(ChangPassRequest changPassRequest);

  User banUser(Integer id);

  User unbanUser(Integer id);

  boolean isUserExist(String username);
}
