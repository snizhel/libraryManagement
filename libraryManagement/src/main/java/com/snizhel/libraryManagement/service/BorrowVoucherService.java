package com.snizhel.libraryManagement.service;




import com.snizhel.libraryManagement.BorrowVoucher;

import java.util.List;

public interface BorrowVoucherService {
  List< BorrowVoucher > findAll();

  BorrowVoucher findOne(Integer id);

  BorrowVoucher save( BorrowVoucher borrowVoucher);

  void delete(Integer id);

  void update(Integer id, BorrowVoucher borrowVoucher);
}
