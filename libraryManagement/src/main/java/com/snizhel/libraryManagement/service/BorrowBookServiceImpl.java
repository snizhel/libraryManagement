package com.snizhel.libraryManagement.service;

import com.snizhel.libraryManagement.model.Book;
import com.snizhel.libraryManagement.model.BorrowBook;
import com.snizhel.libraryManagement.model.EStatus;
import com.snizhel.libraryManagement.model.User;
import com.snizhel.libraryManagement.payload.BookDto;
import com.snizhel.libraryManagement.payload.BorrowBookDto;
import com.snizhel.libraryManagement.payload.CustomerDto;
import com.snizhel.libraryManagement.repository.BorrowBookRepository;
import com.snizhel.libraryManagement.security.AuthTokenFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BorrowBookServiceImpl implements BorrowBookService {
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @Autowired
    private BorrowBookRepository borrowBookRepository;

    @Override
    public BorrowBook createBorrowBook(BorrowBookDto borrowBookDto) {
        BorrowBook borrowBook = new BorrowBook(borrowBookDto.getDateOfBorrow(), borrowBookDto.getExpiredTerm());
        try {
            CustomerDto customerDto = borrowBookDto.getUserId();
            User user = userService.findById(customerDto.getId());
            borrowBook.setUserId(user);
            Set<BookDto> bookDtos = borrowBookDto.getBooks();
            Set<Book> books = new HashSet<>();
            for (BookDto bookDto : bookDtos) {
                if (bookDto.getQuantity() == 0) {
                    throw new IllegalArgumentException("Book with id " + bookDto.getId() + " not found");
                }
                Book book = bookService.findById(bookDto.getId());
                book.setQuantity(book.getQuantity() - 1);
                books.add(book);
            }
            borrowBook.setUserId(user);
            borrowBook.setBooks(books);
            borrowBook.getBooks().add(bookService.findById(bookDtos.iterator().next().getId()));
            borrowBook.setStatus(EStatus.PENDING);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return borrowBookRepository.save(borrowBook);
    }

    @Override
    public BorrowBook deleteBorrowBook(Integer id) {
        BorrowBook borrowBook = borrowBookRepository.findById(id).orElse(null);
        if (borrowBook == null) {
            throw new IllegalArgumentException("BorrowBook with id " + id + " not found");
        }
        Set<Book> books = borrowBook.getBooks();
        Set<Book> booksPay = new HashSet<>();
        for (Book book : books) {
            book.setQuantity(book.getQuantity() + 1);
            booksPay.add(book);
        }
        borrowBook.setBooks(booksPay);
        borrowBookRepository.delete(borrowBook);
        return borrowBook;
    }

    @Override
    public BorrowBook setStatus(Integer id) {
        for (BorrowBook borrowBook : borrowBookRepository.findAll()) {
            if (borrowBook.getId().equals(id)) {
                System.err.println(borrowBook.getUserId());
                borrowBook.setStatus(EStatus.BORROWED);
                borrowBookRepository.save(borrowBook);
                return borrowBook;
            }
        }
        throw new IllegalArgumentException("BorrowBook with id " + id + " not found");
    }

    @Override
    public List<BorrowBook> getAllBorrowBook() {
        return borrowBookRepository.findAll();
    }

    @Override
    public List<BorrowBook> getAllBorrowBookByUserId(Integer id) {
        return borrowBookRepository.findByUserId(id);
    }
}
