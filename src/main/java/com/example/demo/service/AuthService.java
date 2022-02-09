package com.example.demo.service;

import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.dto.response.JwtResponse;
import com.example.demo.dto.response.RegisterResponse;

public interface AuthService {

	JwtResponse login(LoginRequest loginRequest);

	RegisterResponse register(RegisterRequest registerRequest);

}
