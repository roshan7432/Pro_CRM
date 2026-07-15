package com.roshan.crm.service.impl;

import com.roshan.crm.dto.RegisterRequest;
import com.roshan.crm.entity.User;
import com.roshan.crm.repository.UserRepository;
import com.roshan.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
             throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        
        // Handle role from request or default to SALES_EXECUTIVE
        if (request.getRole() != null && !request.getRole().isEmpty()) {
             user.setRole(request.getRole().replace("ROLE_", ""));
        } else {
             user.setRole("SALES_EXECUTIVE");
        }

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
