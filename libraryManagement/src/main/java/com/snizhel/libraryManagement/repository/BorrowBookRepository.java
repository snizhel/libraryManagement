package com.snizhel.libraryManagement.repository;

import com.snizhel.libraryManagement.model.BorrowBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BorrowBookRepository extends JpaRepository<BorrowBook, Integer> {
    @Query("SELECT b FROM BorrowBook b WHERE b.id = ?1")
    Optional<BorrowBook> findById(Integer id);

    @Query(value = "SELECT * FROM borrow_book  WHERE borrow_book.customer_id = ?1", nativeQuery = true)
    List<BorrowBook> findByUserId(Integer id);

}