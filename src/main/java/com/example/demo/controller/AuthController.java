package com.example.demo.controller;

 import javax.validation.Valid;

import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.dto.response.JwtResponse;
import com.example.demo.dto.response.RegisterResponse;
import com.example.demo.serviceImp.AuthServiceImp;

import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	private final AuthServiceImp authServiceImp ;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		JwtResponse jwtResponse = authServiceImp.login(loginRequest)  ;  
		return ResponseEntity.ok(  jwtResponse  );
	}

	@PostMapping("/register")
	public ResponseEntity<RegisterResponse> registerUser(@Valid @RequestBody RegisterRequest signUpRequest) throws JSONException {
		RegisterResponse user = authServiceImp.register(signUpRequest) ;
 		return  new ResponseEntity<RegisterResponse>( user  , HttpStatus.OK ) ;
	}
} 
