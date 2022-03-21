package com.snizhel.libraryManagement.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
    name = "customer",
    uniqueConstraints = {
      @UniqueConstraint(columnNames = "name"),
    })
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idcustomer", nullable = false)
  private Integer id;

  @Column(name = "name", nullable = false, length = 45)
  private String name;

  @Column(name = "password", nullable = false, length = 120)
  private String password;

  @Column(name = "phone", length = 45)
  private String phone;

  @Column(name = "address", length = 45)
  private String address;

  @Column(name = "birthday", length = 45)
  private String birthday;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "user_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  public Customer(String name, String password, String phone, String address, String birthday) {
    this.name = name;
    this.password = password;
    this.phone = phone;
    this.address = address;
    this.birthday = birthday;
  }

  public Customer() {}

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  // TODO Reverse Engineering! Migrate other columns to the entity
}
