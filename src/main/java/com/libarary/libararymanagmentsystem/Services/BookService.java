package com.libarary.libararymanagmentsystem.Services;

import com.libarary.libararymanagmentsystem.Models.Book;
import com.libarary.libararymanagmentsystem.Repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookService  {
    @Autowired
 BookRepo bookRepo;
 public  BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
 }
    // Get all books\\
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    // Get book by ID
    public Book getBookById(int id) {
        return bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    // Add new book
    public Book addBook(Book book) {
        return bookRepo.save(book);
    }

    // Update book
    public Book updateBook(int id, Book updatedBook) {
        Book existing = getBookById(id);
        existing.setTitle(updatedBook.getTitle());
        existing.setAuthor(updatedBook.getAuthor());
        existing.setIsbn(updatedBook.getIsbn());
        return bookRepo.save(existing);
    }

    // Delete book
    public void deleteBook(int id) {
        bookRepo.deleteById(id);
    }

    // Search by title
    public List<Book> searchByTitle(String title) {
        return bookRepo.findByTitleContainingIgnoreCase(title);
    }

    // Search by author
    public List<Book> searchByAuthor(String author) {
        return bookRepo.findByAuthorContainingIgnoreCase(author);
    }
    // Check if book is available
    public boolean isAvailable(int id) {
        Book book = getBookById(id);
        return book.isAvailable(); // من الـ Model
    }

}
