package com.roshan.crm.controller;

import com.roshan.crm.dto.AuthResponse;
import com.roshan.crm.dto.LoginRequest;
import com.roshan.crm.dto.RegisterRequest;
import com.roshan.crm.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request) {

        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LoginRequest request) {

        String token = authService.login(request);

        return new AuthResponse(token);
    }
}