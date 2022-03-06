package com.example.demo.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
 
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

	public User(Long id2, String username2, String username3) {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(max = 40)
	@NotBlank(message = "Please enter a valid username")
	private String username;

	@Size(max = 60)
	@Email
	@NotBlank(message = "Please enter a valid email")
	private String email;

	@NotBlank(message = "Please enter a valid password")
	private String password;

	@NotBlank(message = "Please enter a valid role")
	private String role;

	@OneToMany(mappedBy = "user")
    List<UserStore> userStores;
}
