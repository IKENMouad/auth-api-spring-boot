package com.example.demo.service;

import java.util.List;
import java.util.Set;

import com.example.demo.dto.request.PermissionRequest;
import com.example.demo.models.Permission;
import com.example.demo.models.User;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PermissionService {

    private final PermissionRepository permissionRepository;
    private final UserRepository userRepository;

    public List<Permission> getPermissions() {
        return permissionRepository.findAll();
    }

    public Permission createPermission(PermissionRequest permissionRequest) {
        Permission permission = new Permission();
        BeanUtils.copyProperties(permissionRequest, permission);
        return permissionRepository.save(permission);
    }

    public String attachPermissionToUser(long userId, long permissionId) {
        User user = userRepository.findById(userId).get();
        Permission permission = permissionRepository.findById(permissionId).get();
        if (user == null) {
            return "failure : Username not found" + userId;
        } else if (permission == null) {
            return "failure : permission not found" + permissionId;
        } else {
            if (!user.getPermissions().contains(permission)) {
                user.getPermissions().add(permission);
                userRepository.save(user);
            }
            return (user.getPermissions().contains(permission) ? "success" : "failure");
        }
    }

    public String attachPermissionsToUser(long userId, Set<PermissionRequest> permissionRequests) {
        User user = userRepository.findById(userId).get();
        if (user == null) {
            return "failure : Username not found" + userId;
        } else {
            permissionRequests.forEach(permissionRequest -> {
                Permission permission = permissionRepository.findById(permissionRequest.getId()).get();
                if (permission != null) {
                    if (!user.getPermissions().contains(permission)) {
                        user.getPermissions().add(permission);
                        userRepository.save(user);
                    }
                }
            });
            return "success";
        }
    }

}
