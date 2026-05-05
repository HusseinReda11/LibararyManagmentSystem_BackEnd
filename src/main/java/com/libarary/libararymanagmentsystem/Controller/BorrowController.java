package com.libarary.libararymanagmentsystem.Controller;

import com.libarary.libararymanagmentsystem.Models.Borrow;
import com.libarary.libararymanagmentsystem.Services.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/borrow")
public class BorrowController {
    @Autowired
    BorrowService borrowService;
@GetMapping
    public ResponseEntity<List<Borrow>> getAllBorrows() {
    return ResponseEntity.ok(borrowService.getAllBorrows());
}

    @GetMapping("/{id}")
    public ResponseEntity<Borrow> getBorrowById(@PathVariable int id) {
        return ResponseEntity.ok(borrowService.getBorrowById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Borrow> borrowBook(
            @RequestParam int userId,
            @RequestParam int bookId) {
        return ResponseEntity.ok(borrowService.borrowBook(userId, bookId));
    }

    @PutMapping("/return/{id}")
    public ResponseEntity<Borrow> returnBook(@PathVariable int id) {
        return ResponseEntity.ok(borrowService.returnBook(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Borrow>> getBorrowsByUser(@PathVariable int userId) {
        return ResponseEntity.ok(borrowService.getBorrowsByUser(userId));
    }

    @GetMapping("/active")
    public ResponseEntity<List<Borrow>> getActiveBorrows() {
        return ResponseEntity.ok(borrowService.getActiveBorrows());
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<Borrow>> getOverdueBorrows() {
        return ResponseEntity.ok(borrowService.getOverdueBorrows());
    }
    }
