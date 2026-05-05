package com.libarary.libararymanagmentsystem.Repository;

import com.libarary.libararymanagmentsystem.Models.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BorrowRepo extends JpaRepository<Borrow,Integer> {
    List<Borrow> findByUserId(int userId);

    List<Borrow> findByReturnDateIsNull();

    List<Borrow> findByReturnDateIsNullAndBorrowDateBefore(LocalDate overdueDate);
}
