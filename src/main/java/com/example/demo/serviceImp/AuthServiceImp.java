package com.example.demo.serviceImp;

import com.example.demo.models.Role;
import com.example.demo.repository.RoleRepository;

import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.example.demo.config.security.jwt.JwtUtils;
import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;

	@Override
	public String login(LoginRequest loginRequest) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
					.collect(Collectors.toList());
			jsonObject.put("token", jwt);
			jsonObject.put("roles", roles);
			jsonObject.put("username", userDetails.getUsername());
			jsonObject.put("email", userDetails.getEmail());
			jsonObject.put("id", userDetails.getId());

		} catch (BadCredentialsException e) {
			jsonObject.put("status", "Error: Bad Credentials ");
			jsonObject.put("status", "failure");
		}
		return jsonObject.toString();
	}

	@Override
	public String register(RegisterRequest signUpRequest) throws Exception {
		JSONObject jsonObject = new JSONObject();

		if (userRepository.existsByUsername(signUpRequest.getName())) {
			jsonObject.put("status", "Error: Username is already taken!");
			return jsonObject.toString();
		}
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			jsonObject.put("status", "Error: Email is already taken!");
			return jsonObject.toString();
		}
		// Create new user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getEmail(),
				passwordEncoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRoles();

		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role userRole = roleRepository.findByName("ROLE_USER")
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				if (role.equals("admin")) {
					Role adminRole = roleRepository.findByName("ROLE_ADMIN")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
				} else {
					Role userRole = roleRepository.findByName("ROLE_USER")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		user = userRepository.save(user);
		Set<String> rolesResponse = new HashSet<>();
		user.getRoles().forEach(role -> {
			rolesResponse.add(role.getName());
		});
		jsonObject.put("id", user.getId());
		jsonObject.put("username", user.getUsername());
		jsonObject.put("email", user.getEmail());
		jsonObject.put("roles", rolesResponse);
		jsonObject.put("status", user == null ? "failed" : "success");
		return jsonObject.toString();
	}
}
