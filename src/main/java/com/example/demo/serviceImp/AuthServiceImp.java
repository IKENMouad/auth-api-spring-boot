package com.example.demo.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;

@Service
public class AuthServiceImp implements AuthService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public String login(LoginRequest loginRequest) {
		User fetchedUser = userRepo.findByEmail(loginRequest.getUsername());
		if (fetchedUser == null) {

			return "";
		} else

			return null;
	}

	@Override
	public String register(RegisterRequest registerRequest) {
		User fetchedUser = userRepo.findByEmail(registerRequest.getEmail()  );
		if (fetchedUser == null) {
			
			return "";
		} else

			return null;
	}
}
