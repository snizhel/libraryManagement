package com.snizhel.libraryManagement.controller;


import com.snizhel.libraryManagement.BorrowVoucher;
import com.snizhel.libraryManagement.service.BorrowVoucherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/vouchers")
//@PreAuthorize("hasRole('USER')")
public class BorrowVoucherController {
  @Autowired private BorrowVoucherServiceImpl borrowVoucherService;

  @GetMapping
  public ResponseEntity<List< BorrowVoucher >> getAllVouchers() {
    List<BorrowVoucher> vouchers = borrowVoucherService.findAll();
    return new ResponseEntity<>(vouchers, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<BorrowVoucher> createVoucher(@RequestBody BorrowVoucher voucher) {
    BorrowVoucher voucher1 = borrowVoucherService.save(voucher);
    HttpHeaders headers = new HttpHeaders();
    headers.add("Location", "/vouchers/" + voucher1.getId());
    return new ResponseEntity<>(voucher1, headers, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<BorrowVoucher> editVoucher(
      @PathVariable("id") Integer id, @RequestBody BorrowVoucher voucher) {
    borrowVoucherService.update(id, voucher);
    return new ResponseEntity<>(borrowVoucherService.findOne(id), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BorrowVoucher> deleteVoucher(@PathVariable("id") Integer id) {
    borrowVoucherService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
