package com.roshan.crm.service;

import com.roshan.crm.dto.LoginRequest;
import com.roshan.crm.dto.RegisterRequest;

public interface AuthService {

    String register(RegisterRequest request);

    String login(LoginRequest request);
}