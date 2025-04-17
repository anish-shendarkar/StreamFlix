package com.example.streamflix.controller;

import com.example.streamflix.model.User;
import com.example.streamflix.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        return authService.signup(user);
    }

    @PostMapping("/signin")
    public String signin(@RequestParam String username, @RequestParam String password) {
        return authService.signin(username, password);
    }
}
