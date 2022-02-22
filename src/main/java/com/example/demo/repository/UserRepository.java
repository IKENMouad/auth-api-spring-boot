package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	@Query(value = "SELECT users.* FROM users INNER JOIN user_roles ON users.id = user_roles.user_id INNER JOIN roles ON roles.id = user_roles.role_id WHERE roles.name like ?1", nativeQuery = true)
	List<User> findUsersByRoleName(String roleName);
}
