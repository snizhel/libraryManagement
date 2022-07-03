package com.snizhel.libraryManagement.payload;

import com.snizhel.libraryManagement.model.Role;

import javax.persistence.Column;
import java.util.Set;

public class SignupRequest {

  @Column(length = 45, nullable = false)
  private String username;

  private Set<String> role;

  @Column(length = 45, nullable = false)
  private String password;

  @Column(length = 45)
  private String phone;

  @Column(length = 45)
  private String address;

  @Column(length = 45)
  private String birthday;

  @Column
  private int status;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Set<String> getRole() {
    return role;
  }

  public void setRole(Set<String> role) {
    this.role = role;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }
}
