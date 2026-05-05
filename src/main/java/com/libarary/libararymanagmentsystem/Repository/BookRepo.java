package com.libarary.libararymanagmentsystem.Repository;

import com.libarary.libararymanagmentsystem.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<Book,Integer> {
    List<Book> findByTitleContaining(String title);

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByAuthorContainingIgnoreCase(String author);


}
