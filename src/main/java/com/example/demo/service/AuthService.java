package com.example.demo.service;

import java.util.Map;

import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.RegisterRequest;
 
public interface AuthService {

	Map<String, Object> login(LoginRequest loginRequest) throws Exception;

	Map<String, Object> register(RegisterRequest registerRequest) throws Exception;

}
