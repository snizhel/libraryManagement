package com.snizhel.libraryManagement.model;

import javax.persistence.*;

@Entity
@Table( name = "book" )
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column( name = "idbook", nullable = false )
    private Integer id;

    @Column( name = "title", nullable = false, length = 45 )
    private String title;

    @Column( name = "author", length = 45 )
    private String author;

    @Column( name = "publisher", length = 45 )
    private String publisher;

    @Column( name = "price", length = 45 )
    private String price;

    @Column( name = "quantity", length = 45 )
    private String quantity;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity( String quantity ) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice( String price ) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher( String publisher ) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor( String author ) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }
}