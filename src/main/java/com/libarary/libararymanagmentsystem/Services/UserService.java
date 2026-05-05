package com.libarary.libararymanagmentsystem.Services;

import com.libarary.libararymanagmentsystem.Models.User;
import com.libarary.libararymanagmentsystem.Models.UserType;
import com.libarary.libararymanagmentsystem.Repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    UserRepo userRepo;
    PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }
    // Get all users
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    // Get user by ID
    public User getUserById(int id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    // Get user by username
    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }

    // Register new user
    public User registerUser(User user) {
        // Check if username already exists
        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists!");
        }
        // Hash password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Default role
        if (user.getRole() == null) {
            user.setRole(UserType.USER);
        }
        return userRepo.save(user);
    }

    // Update user
    public User updateUser(int id, User updatedUser) {
        User existing = getUserById(id);
        existing.setUsername(updatedUser.getUsername());
        existing.setEmail(updatedUser.getEmail());
        // Only update password if provided
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            existing.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
        return userRepo.save(existing);
    }

    // Delete user
    public void deleteUser(int id) {
        userRepo.deleteById(id);
    }

    // Change user role
    public User changeUserType(int id, UserType newType) {
        User user = getUserById(id);
        user.setRole(newType);
        return userRepo.save(user);
    }
}