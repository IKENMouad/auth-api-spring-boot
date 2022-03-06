package com.example.demo.serviceImp;

import java.util.HashMap;
import java.util.Map;

import com.example.demo.config.security.jwt.JwtUtils;
import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;

	@SneakyThrows
	@Override
	public Map<String, Object> login(LoginRequest loginRequest) {
		Map<String, Object> mapObjects = new HashMap<String, Object>();
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

			User user = new User();
			user.setId(userDetails.getId());
			user.setUsername(userDetails.getUsername());
			user.setEmail(userDetails.getEmail());

			mapObjects.put("token", jwt);
			mapObjects.put("user", user);
		} catch (Exception e) {
			if (e instanceof BadCredentialsException) {
				mapObjects.put("error", "Error: Bad Credentials " + e.getMessage());
			} else {
				mapObjects.put("error", "Error: " + e.getMessage());
			}
		}
		return mapObjects;
	}

	@SneakyThrows
	@Override
	public Map<String, Object> register(RegisterRequest signUpRequest) {
		Map<String, Object> mapObjects = new HashMap<String, Object>();
		if (userRepository.getByUsername(signUpRequest.getName()) != null) {
			mapObjects.put("error", "Error: Email is already taken!");
		} else if (userRepository.getByEmail(signUpRequest.getEmail()) != null) {
			mapObjects.put("error", "Error: Name is already taken!");
		} else {
			// Create new user's account
			User user = new User();
			user.setEmail(signUpRequest.getEmail());
			user.setUsername(signUpRequest.getName());
			user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
			user.setRole(Role.ROLE_USER);
			mapObjects.put("user", userRepository.save(user));
		}
		return mapObjects;
	}
}
