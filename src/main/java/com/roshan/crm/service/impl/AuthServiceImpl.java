package com.roshan.crm.service.impl;

import com.roshan.crm.dto.LoginRequest;
import com.roshan.crm.dto.RegisterRequest;
import com.roshan.crm.entity.User;
import com.roshan.crm.repository.UserRepository;
import com.roshan.crm.security.JwtService;
import com.roshan.crm.service.AuthService;
import com.roshan.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

    @Override
    public String register(RegisterRequest request) {
        userService.createUser(request);
        return "User Registered Successfully";
    }

    @Override
    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User Not Found"));

        boolean match = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!match) {
            throw new RuntimeException("Invalid Password");
        }

        return jwtService.generateToken(user.getEmail());
    }
}
