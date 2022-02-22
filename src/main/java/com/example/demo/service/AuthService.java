package com.example.demo.service;

import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.RegisterRequest;

public interface AuthService {

	String login(LoginRequest loginRequest) throws Exception;

	String register(RegisterRequest registerRequest) throws Exception;

}
