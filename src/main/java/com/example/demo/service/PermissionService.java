package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.request.PermissionRequest;
import com.example.demo.models.Permission;
import com.example.demo.repository.PermissionRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PermissionService {

    private final PermissionRepository permissionRepository; 

    public List<Permission> getPermissions() {
        return permissionRepository.findAll();
    }

    public Permission createPermission(PermissionRequest permissionRequest) {
        Permission permission = new Permission();
        BeanUtils.copyProperties(permissionRequest, permission);
        return permissionRepository.save(permission);
    }

   
}
