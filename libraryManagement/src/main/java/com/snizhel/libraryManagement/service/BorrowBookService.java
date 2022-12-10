package com.snizhel.libraryManagement.service;

import com.snizhel.libraryManagement.model.BorrowBook;
import com.snizhel.libraryManagement.payload.BorrowBookDto;

import java.util.List;

public interface BorrowBookService {
    BorrowBook createBorrowBook(BorrowBookDto borrowBookDto);

    BorrowBook deleteBorrowBook(Integer id);

    BorrowBook setStatus(Integer id);

    List<BorrowBook> getAllBorrowBook();

    List<BorrowBook> getAllBorrowBookByUserId(Integer id);
}
