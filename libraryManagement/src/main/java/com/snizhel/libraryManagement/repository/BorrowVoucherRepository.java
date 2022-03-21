package com.snizhel.libraryManagement.repository;


import com.snizhel.libraryManagement.BorrowVoucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowVoucherRepository extends JpaRepository<BorrowVoucher, Integer > {
}