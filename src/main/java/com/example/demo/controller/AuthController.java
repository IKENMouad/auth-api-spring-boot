package com.example.demo.controller;

import javax.validation.Valid;

import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.serviceImp.AuthServiceImp;
import com.example.demo.utils.ErrorUtils;

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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthServiceImp authServiceImp;

	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result)
			throws Exception {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(ErrorUtils.customErrors(result.getAllErrors()));
		} else {
			String loginResponse = authServiceImp.login(loginRequest);
			return ResponseEntity.ok(loginResponse);
		}
	}

	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterRequest signUpRequest, BindingResult result)
			throws Exception {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(ErrorUtils.customErrors(result.getAllErrors()));
		} else {
			String registerResponse = authServiceImp.register(signUpRequest);
			return new ResponseEntity<String>(registerResponse, HttpStatus.CREATED);
		}
	}

}
