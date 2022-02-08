package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.enums.ERole;
import com.example.demo.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long>  {
   
	Optional<Role> findByName(ERole eRole)  ;
	
}
