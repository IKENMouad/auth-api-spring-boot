package com.example.demo.dto.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterRequest {

	@NotBlank(message = "Please enter a valid name")
	private String name;
	
	@NotBlank(message = "Please enter a valid email")
	@Email(message = "Please enter a valid email")
	private String email;

	@NotBlank(message = "Please enter a valid password")
	@Size(min = 4, message = "Password must be at least 4 characters")
	private String password;

	private Set<String> roles;

}
