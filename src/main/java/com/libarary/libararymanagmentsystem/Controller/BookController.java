package com.libarary.libararymanagmentsystem.Controller;

import com.libarary.libararymanagmentsystem.Models.Book;
import com.libarary.libararymanagmentsystem.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping
            public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // GET /books/{id} → كتاب واحد
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.addBook(book));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book) {
        return ResponseEntity.ok(bookService.updateBook(id, book));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book deleted successfully");
    }

    @GetMapping("/search/title")
    public ResponseEntity<List<Book>> searchByTitle(@RequestParam String title) {
        return ResponseEntity.ok(bookService.searchByTitle(title));
    }

    @GetMapping("/search/author")
    public ResponseEntity<List<Book>> searchByAuthor(@RequestParam String author) {
        return ResponseEntity.ok(bookService.searchByAuthor(author));
    }

    @GetMapping("/available/{id}")
    public ResponseEntity<Boolean> isAvailable(@PathVariable int id) {
        return ResponseEntity.ok(bookService.isAvailable(id));
    }
}
