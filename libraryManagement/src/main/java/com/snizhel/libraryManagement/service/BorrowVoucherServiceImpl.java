package com.snizhel.libraryManagement.service;

import com.snizhel.libraryManagement.BorrowVoucher;
import com.snizhel.libraryManagement.repository.BorrowVoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BorrowVoucherServiceImpl implements BorrowVoucherService {
  @Autowired private BorrowVoucherRepository borrowVoucherRepository;

  @Override
  public List<BorrowVoucher> findAll() {
    List<BorrowVoucher> borrowVouchers = new ArrayList<>();
    borrowVoucherRepository.findAll().forEach(borrowVouchers::add);
    return borrowVouchers;
  }

  @Override
  public BorrowVoucher findOne(Integer id) {
    return borrowVoucherRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Not found this item"));
  }

  @Override
  public BorrowVoucher save(BorrowVoucher borrowVoucher) {
    return borrowVoucherRepository.save(borrowVoucher);
  }

  @Override
  public void delete(Integer id) {
    borrowVoucherRepository.deleteById(id);
  }

  @Override
  public void update(Integer id, BorrowVoucher borrowVoucher) {

  }
}
