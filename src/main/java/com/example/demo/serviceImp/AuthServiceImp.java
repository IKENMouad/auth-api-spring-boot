package com.example.demo.serviceImp;

 import com.example.demo.dto.response.JwtResponse;
import com.example.demo.dto.response.RegisterResponse;
import com.example.demo.models.Role;
import com.example.demo.repository.RoleRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.jwt.JwtUtils;
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
	private final PasswordEncoder  passwordEncoder  ;
	private final AuthenticationManager authenticationManager;
 	private final JwtUtils jwtUtils;

	 

	@Override
	public JwtResponse login(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
	SecurityContextHolder.getContext().setAuthentication(authentication);
	String jwt = jwtUtils.generateJwtToken(authentication);

	UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
	List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
			.collect(Collectors.toList());

			return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles);
	}

	@Override
	public RegisterResponse register(RegisterRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getName())) {
			return  new RegisterResponse("Error: Username is already taken!");
		}
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return  new RegisterResponse("Error: Email is already taken!");
		}
		// Create new user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getEmail(),
				passwordEncoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRoles();

 		Set<Role> roles = new HashSet<>();
		if (strRoles == null     ) {
 			Role userRole = roleRepository.findByName("ROLE_USER")
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
 			strRoles.forEach(role -> {
				if(role.equals("admin")){
					Role adminRole = roleRepository.findByName("ROLE_ADMIN")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
				}
				else{
					Role userRole = roleRepository.findByName("ROLE_USER")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}
  		user.setRoles(roles);
		user   =userRepository .save(user);
		Set<String> rolesResponse = new HashSet<>()  ;
		user.getRoles().forEach(role->{
			rolesResponse.add(role.getName()) ;
		}) ;
		RegisterResponse registerResponse = new RegisterResponse( user.getId()  ,  user.getUsername() , user.getEmail() ,  rolesResponse , user ==null  ? "failed" :"success" );
		return registerResponse ;
	}
}
