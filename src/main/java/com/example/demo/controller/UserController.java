package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import com.example.demo.models.User;
import com.example.demo.serviceImp.UserServiceApi;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceApi userService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUser(@PathVariable long id) throws Exception {
        Map<String, Object> mapItems = new HashMap<String, Object>();
        try {
            User user = userService.getUserById(id);
            mapItems.put("user", user);
            mapItems.put("status", user == null ? "failure " : "success");
        } catch (Exception e) {
            if (e instanceof NoSuchElementException) {
                mapItems.put("status", "failure :" + " user not found with id " + id);
            }
        }
        String jsonResponse = new ObjectMapper().writeValueAsString(mapItems);
        return ResponseEntity.ok(jsonResponse);
    }

    @GetMapping("/byrole")
    public List<User> getUsersByRole(
            @RequestParam(name = "role", required = true, defaultValue = "ROLE_USER") String roleName) {
        return userService.getUsersByRole(roleName);
    }

}
