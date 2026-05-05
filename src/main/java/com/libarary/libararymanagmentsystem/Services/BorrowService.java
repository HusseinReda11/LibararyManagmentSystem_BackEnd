package com.libarary.libararymanagmentsystem.Services;

import com.libarary.libararymanagmentsystem.Models.Book;
import com.libarary.libararymanagmentsystem.Models.Borrow;
import com.libarary.libararymanagmentsystem.Models.User;
import com.libarary.libararymanagmentsystem.Repository.BookRepo;
import com.libarary.libararymanagmentsystem.Repository.BorrowRepo;
import com.libarary.libararymanagmentsystem.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowService {
    @Autowired
 BookRepo bookRepo;
    @Autowired
 UserRepo userRepo;
    @Autowired
 BorrowRepo borrowRepo;

    public BorrowService(BorrowRepo borrowRepo, BookRepo bookRepo, UserRepo userRepo) {
        this.borrowRepo = borrowRepo;
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
    }

    public List<Borrow> getAllBorrows() {
        return borrowRepo.findAll();
    }

    public Borrow getBorrowById(int id) {
        return borrowRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrow record not found: " + id));
    }
    public Borrow borrowBook(int userId, int bookId) {
        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (!book.isAvailable()) {
            throw new RuntimeException("Book is not available!");
        }

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Borrow borrow = new Borrow();
        borrow.setBook(book);
        borrow.setUser(user);
        borrow.setBorrowDate(LocalDate.now());
        borrow.setReturnDate(null);

        book.setAvailable(false);
        bookRepo.save(book);

        return borrowRepo.save(borrow);
    }

    public Borrow returnBook(int borrowId) {
        Borrow borrow = getBorrowById(borrowId);

        borrow.setReturnDate(LocalDate.now());

        Book book = borrow.getBook();
        book.setAvailable(true);
        bookRepo.save(book);

        return borrowRepo.save(borrow);
    }

    public List<Borrow> getBorrowsByUser(int userId) {
        return borrowRepo.findByUserId(userId);
    }

    public List<Borrow> getActiveBorrows() {
        return borrowRepo.findByReturnDateIsNull();
    }

    public List<Borrow> getOverdueBorrows() {
        LocalDate overdueDate = LocalDate.now().minusDays(14);
        return borrowRepo.findByReturnDateIsNullAndBorrowDateBefore(overdueDate);
    }
}