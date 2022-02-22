package com.example.demo.serviceImp;

import java.util.List;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceApi {

    private final UserRepository userRepository;

    public User getUserById(long id) {
        return userRepository.findById(id).get() ; 
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsersByRole(String rolename) {
        List<User> users = userRepository.findUsersByRoleName( rolename );
        return users;
    }
 

}