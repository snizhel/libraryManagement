package com.snizhel.libraryManagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.snizhel.libraryManagement.view.BorrowBookView;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idbook", nullable = false)
  private Integer id;

  @Column(name = "title", nullable = false, length = 45)
  @JsonView(BorrowBookView.Public.class)
  private String title;

  @Column(name = "author", length = 45)
  private String author;

  @Column(name = "publisher", length = 45)
  private String publisher;

  @Column(name = "quantity", length = 45)
  private Integer quantity;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JsonBackReference
  @JoinTable(
      name = "borrow_vouchers",
      joinColumns = @JoinColumn(name = "book_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  private Set<BorrowBook> users = new HashSet<>();

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Set<BorrowBook> getUsers() {
    return users;
  }

  public void setUsers(Set<BorrowBook> users) {
    this.users = users;
  }
}
