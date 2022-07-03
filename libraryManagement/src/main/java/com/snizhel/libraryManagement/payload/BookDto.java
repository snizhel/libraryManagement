package com.snizhel.libraryManagement.payload;

import java.io.Serializable;
import java.util.Objects;

public class BookDto implements Serializable {

    private final Integer id;
    private final String title;
    private final String author;
    private final String publisher;
    private final Integer quantity;

    public BookDto(Integer id, String title, String author, String publisher, Integer quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto entity = (BookDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.title, entity.title) &&
                Objects.equals(this.author, entity.author) &&
                Objects.equals(this.publisher, entity.publisher) &&
                Objects.equals(this.quantity, entity.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, publisher, quantity);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "title = " + title + ", " +
                "author = " + author + ", " +
                "publisher = " + publisher + ", " +
                "quantity = " + quantity + ")";
    }
}
