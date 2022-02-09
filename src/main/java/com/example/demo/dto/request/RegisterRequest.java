package com.example.demo.dto.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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

	@NotBlank
	private String name;
	@NotBlank
	@Email
	private String email;

	private String password;

	private Set<String> roles;

}
