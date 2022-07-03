package com.snizhel.libraryManagement.payload;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class BorrowBookDto implements Serializable {
    private final String dateOfBorrow;
    private final String expiredTerm;
    private final Set<BookDto> books;
    private final CustomerDto userid;

    public BorrowBookDto(String dateOfBorrow, String expiredTerm, Set<BookDto> books, CustomerDto userid) {
        this.dateOfBorrow = dateOfBorrow;
        this.expiredTerm = expiredTerm;
        this.books = books;
        this.userid = userid;
    }

    public String getDateOfBorrow() {
        return dateOfBorrow;
    }

    public String getExpiredTerm() {
        return expiredTerm;
    }

    public Set<BookDto> getBooks() {
        return books;
    }

    public CustomerDto getUserId() {
        return userid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorrowBookDto entity = (BorrowBookDto) o;
        return Objects.equals(this.dateOfBorrow, entity.dateOfBorrow) &&
                Objects.equals(this.expiredTerm, entity.expiredTerm) &&
                Objects.equals(this.books, entity.books) &&
                Objects.equals(this.userid, entity.userid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOfBorrow, expiredTerm, books, userid);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "dateOfBorrow = " + dateOfBorrow + ", " +
                "expiredTerm = " + expiredTerm + ", " +
                "books = " + books + ", " +
                "user = " + userid + ")";
    }
}
