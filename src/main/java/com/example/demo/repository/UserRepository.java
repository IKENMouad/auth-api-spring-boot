package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

}
