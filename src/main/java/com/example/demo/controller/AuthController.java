package com.example.demo.controller;

import java.util.Map;

import javax.validation.Valid;

import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.serviceImp.AuthServiceImp;
import com.example.demo.utils.ErrorUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthServiceImp authServiceImp;

	@SneakyThrows
	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> authenticateUser(@Valid @RequestBody LoginRequest loginRequest,
			BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(ErrorUtils.customErrors(result.getAllErrors()));
		} else {
			Map<String, Object> loginResult = authServiceImp.login(loginRequest);
			String response = new ObjectMapper().writeValueAsString(loginResult);
			return ResponseEntity.ok(response);
		}
	}

	@SneakyThrows
	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest signUpRequest, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(ErrorUtils.customErrors(result.getAllErrors()));
		} else {
			Map<String, Object> resultMap = authServiceImp.register(signUpRequest);
			String response = new ObjectMapper().writeValueAsString(resultMap);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		}
	}
}
