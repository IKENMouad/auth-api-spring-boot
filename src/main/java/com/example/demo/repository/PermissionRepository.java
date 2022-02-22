package com.example.demo.repository;

import com.example.demo.models.Permission;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository  extends JpaRepository<Permission, Long> {
       Permission getPermissionByName(String name);
 }
