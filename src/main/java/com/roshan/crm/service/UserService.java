package com.roshan.crm.service;

import com.roshan.crm.dto.RegisterRequest;
import com.roshan.crm.entity.User;

import java.util.List;

public interface UserService {
    User createUser(RegisterRequest request);
    List<User> getAllUsers();
    void deleteUser(Long id);
}
