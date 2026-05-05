package com.libarary.libararymanagmentsystem.Security;

import com.libarary.libararymanagmentsystem.Models.User;
import com.libarary.libararymanagmentsystem.Repository.UserRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserDetailsServicee implements UserDetailsService { // ← UserDetailsService بس

    private final UserRepo userRepo;

    public UserDetailsServicee(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRole().name()) // ← USER أو ADMIN
                .build();
    }
}