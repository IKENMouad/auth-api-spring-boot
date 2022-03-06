package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findByEmail(String email);

	User getByUsername(String username);

	User getByEmail(String email);

	List<User> findByRole(String roleName);
}
