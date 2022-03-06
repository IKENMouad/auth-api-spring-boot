package com.example.demo.serviceImp;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.example.demo.models.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDetailsImpl implements UserDetails {

	private Long id;

	private String username;

	private String email;

	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
		return new UserDetailsImpl(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), authorities);
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String toString() {
		return "UserDetailsImpl [authorities=" + authorities + ", email=" + email + ", id=" + id + ", password="
				+ password + ", username=" + username + "]";
	}

	public UserDetailsImpl(Long id2, String username2, String email2, String password2,
			SimpleGrantedAuthority simpleGrantedAuthority) {
	}

}
