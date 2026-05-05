package com.libarary.libararymanagmentsystem.Controller;

import com.libarary.libararymanagmentsystem.Models.User;
import com.libarary.libararymanagmentsystem.Models.UserType;
import com.libarary.libararymanagmentsystem.Repository.UserRepo;
import com.libarary.libararymanagmentsystem.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
 UserService userService;
    @Autowired
     AuthenticationManager authenticationManager;

    public AuthController(UserService userService,  AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword()
                    )
            );
            return ResponseEntity.ok("{\"message\": \"Login successful\", \"username\": \"" + user.getUsername() + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}