package com.snizhel.libraryManagement;

import com.snizhel.libraryManagement.model.Book;
import com.snizhel.libraryManagement.model.Customer;

import javax.persistence.*;

@Entity
@Table(name = "borrow_voucher")
public class BorrowVoucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idborrow_voucher", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idBook", nullable = false)
    private Book idBook;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idUser", nullable = false)
    private Customer idUser;

    @Column(name = "borrow_day", length = 45)
    private String borrowDay;

    @Column(name = "expired_term", length = 45)
    private String expiredTerm;

    public String getExpiredTerm() {
        return expiredTerm;
    }

    public void setExpiredTerm(String expiredTerm) {
        this.expiredTerm = expiredTerm;
    }

    public String getBorrowDay() {
        return borrowDay;
    }

    public void setBorrowDay(String borrowDay) {
        this.borrowDay = borrowDay;
    }

    public Customer getIdUser() {
        return idUser;
    }

    public void setIdUser(Customer idUser) {
        this.idUser = idUser;
    }

    public Book getIdBook() {
        return idBook;
    }

    public void setIdBook(Book idBook) {
        this.idBook = idBook;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}