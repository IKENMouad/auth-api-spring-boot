package com.example.demo.dto.request;

import java.util.Set;

import com.example.demo.models.Role;

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

	private String name; 
	
	private String email ;  
	
	private String  password ;  
	
	private Set< String> roles   ;  
	
}
