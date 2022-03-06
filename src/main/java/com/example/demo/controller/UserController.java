package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import com.example.demo.dto.StoreDto;
import com.example.demo.models.User;
import com.example.demo.serviceImp.UserServiceApi;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceApi userService;

    @SneakyThrows
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUser(@PathVariable long id) {
        Map<String, Object> mapItems = new HashMap<String, Object>();
        try {
            User user = userService.getUserById(id);
            mapItems.put("user", user);
        } catch (Exception e) {
            mapItems.put("error", "failure :" + e.getMessage());
        }
        String jsonResponse = new ObjectMapper().writeValueAsString(mapItems);
        return ResponseEntity.ok(jsonResponse);
    }

    @SneakyThrows
    @PutMapping(value = "/attach-store-to-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> attachStoreToUser(long userId, StoreDto storeDto) {
        Map<String, Object> mapItems = new HashMap<String, Object>();

        String attachmentResponse = userService.attachStoreToUser(userId, storeDto);
        mapItems.put("status", attachmentResponse);

        String jsonResponse = new ObjectMapper().writeValueAsString(mapItems);
        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }

    @SneakyThrows
    @PutMapping(value = "/dettach-store-from-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> dettachStoreFromUser(long userId, StoreDto storeDto) {
        Map<String, Object> mapItems = new HashMap<String, Object>();

        String dettachmentResponse = userService.dettachStoreFromUser(userId, storeDto);
        mapItems.put("status", dettachmentResponse);

        String jsonResponse = new ObjectMapper().writeValueAsString(mapItems);
        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }

    @SneakyThrows
    @PatchMapping(value = "/dettach-store-from-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> enableOrDisableUserFromUser(long userId, StoreDto storeDto) {
        Map<String, Object> mapItems = new HashMap<String, Object>();

        String response = userService.enableOrDisableUserFromUser(userId, storeDto);
        mapItems.put("status", response);

        String jsonResponse = new ObjectMapper().writeValueAsString(mapItems);
        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }

}
