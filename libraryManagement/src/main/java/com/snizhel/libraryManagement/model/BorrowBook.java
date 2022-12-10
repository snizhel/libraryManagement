package com.snizhel.libraryManagement.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "borrow_book")
public class BorrowBook {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idborrow_book", nullable = false)
  private Integer id;

  @Column(name = "date_of_borrow", length = 45)
  private String dateOfBorrow;

  @Column(name = "expired_term", length = 45)
  private String expiredTerm;



  @Column(name = "status", length = 45)
  private EStatus status;

  public EStatus getStatus() {
    return status;
  }
  public void setStatus(EStatus status) {
    this.status = status;
  }
  @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
  @JoinTable(
      name = "borrow_vouchers",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "book_id"))
  private Set<Book> books = new HashSet<>();

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "customer_id")
  private User userid;

  public BorrowBook() {}

  public BorrowBook(String dateOfBorrow, String expiredTerm) {
    this.dateOfBorrow = dateOfBorrow;
    this.expiredTerm = expiredTerm;
  }

  public String getExpiredTerm() {
    return expiredTerm;
  }

  public void setExpiredTerm(String expiredTerm) {
    this.expiredTerm = expiredTerm;
  }

  public String getDateOfBorrow() {
    return dateOfBorrow;
  }

  public void setDateOfBorrow(String dateOfBorrow) {
    this.dateOfBorrow = dateOfBorrow;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Set<Book> getBooks() {
    return books;
  }

  public void setBooks(Set<Book> books) {
    this.books = books;
  }

  public User getUserId() {
    return userid;
  }

  public void setUserId(User userid) {
    this.userid = userid;
  }
}
