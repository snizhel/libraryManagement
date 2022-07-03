package com.snizhel.libraryManagement.model;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BorrowVoucherId implements Serializable {
  private static final long serialVersionUID = -6981196411612459906L;

  @Column(name = "book_id", nullable = false)
  private Integer bookId;


  @Column(name = "user_id", nullable = false)
  private Integer userId;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getBookId() {
    return bookId;
  }

  public void setBookId(Integer bookId) {
    this.bookId = bookId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, bookId);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    BorrowVoucherId entity = (BorrowVoucherId) o;
    return Objects.equals(this.userId, entity.userId) && Objects.equals(this.bookId, entity.bookId);
  }
}
