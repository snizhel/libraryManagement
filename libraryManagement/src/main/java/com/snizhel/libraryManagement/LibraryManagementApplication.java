package com.snizhel.libraryManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class LibraryManagementApplication extends SpringBootServletInitializer {
  public static void main(String[] args) {
    SpringApplication.run(LibraryManagementApplication.class, args);
  }
}
