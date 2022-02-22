package com.example.demo.dto.request;

import javax.validation.constraints.Email; 
import javax.validation.constraints.NotNull;
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
public class LoginRequest {

	@NotNull(message = "Please enter a username")
	@Email(message = "Please enter a valid email")
	private String username;

	@NotNull(message = "Please enter a valid password")
	@Size(min = 4, message = "password must be at least 4 characters")
	private String password;
 

}
