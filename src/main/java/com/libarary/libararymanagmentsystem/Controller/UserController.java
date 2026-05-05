package com.libarary.libararymanagmentsystem.Controller;

import com.libarary.libararymanagmentsystem.Models.User;
import com.libarary.libararymanagmentsystem.Models.UserType;
import com.libarary.libararymanagmentsystem.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

@GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
}

@GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
    return ResponseEntity.ok(userService.getUserById(id));
}

@GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
    return ResponseEntity.ok(userService.getUserByUsername(username));
}

@PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
    return ResponseEntity.ok(userService.updateUser(id, user));
}

@DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
    userService.deleteUser(id);
    return ResponseEntity.ok("User Deleted Successfully");
}

@PutMapping("/role/{id}")
    public ResponseEntity<User> updateRole(@PathVariable int id, @RequestBody UserType role) {
    return ResponseEntity.ok(userService.changeUserType(id, role));
}

}
