package com.libarary.libararymanagmentsystem.Repository;

import com.libarary.libararymanagmentsystem.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String username);

}
