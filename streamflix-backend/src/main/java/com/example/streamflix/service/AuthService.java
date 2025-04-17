package com.example.streamflix.service;

import com.example.streamflix.model.User;
import com.example.streamflix.repository.UserRepository;
import com.example.streamflix.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String signup(User user) {
        if(userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "Signup successful for " + user.getUsername();
    }

    public String signin(String username, String rawPassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Invalid Credentials");
        }

        return jwtUtil.generateToken(user.getUsername(), user.getRole());
    }
}
