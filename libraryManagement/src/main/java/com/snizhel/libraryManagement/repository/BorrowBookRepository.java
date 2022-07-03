package com.snizhel.libraryManagement.repository;

import com.snizhel.libraryManagement.model.BorrowBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowBookRepository extends JpaRepository<BorrowBook, Integer> {
}