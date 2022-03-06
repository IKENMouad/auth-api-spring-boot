package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.demo.dto.StoreDto;
import com.example.demo.dto.UserStoreDto;
import com.example.demo.service.StoreService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/stors/")
@RequiredArgsConstructor
public class StoreController {
    
    private final StoreService storeService;


    @SneakyThrows
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createStore(@Valid @RequestBody StoreDto storeDto) {
        Map<String, Object> mapItems = new HashMap<String, Object>();
        StoreDto store = storeService.createStore(storeDto);
        mapItems.put("store", store) ; 
        String response = new ObjectMapper().writeValueAsString(mapItems);
        return new ResponseEntity<>( response , HttpStatus.OK);
    }


    
    @SneakyThrows
    @PutMapping(value = "{storeId}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStore( @PathVariable long storeId ,   @Valid @RequestBody StoreDto storeDto) {
        Map<String, Object> mapItems = new HashMap<String, Object>();
        StoreDto store = storeService.updateStore(storeId, storeDto);
        mapItems.put("store", store) ; 
        String response = new ObjectMapper().writeValueAsString(mapItems);
        return new ResponseEntity<>( response , HttpStatus.OK);
    }

    @SneakyThrows
    public ResponseEntity<?> getStore( long storeId) {
        Map<String, Object> mapItems = new HashMap<String, Object>();
        StoreDto store = storeService.getStore(storeId);
        mapItems.put("store", store) ; 
        String response = new ObjectMapper().writeValueAsString(mapItems);
        return new ResponseEntity<>( response , HttpStatus.OK);
    }



    @SneakyThrows
    public ResponseEntity<?> getStoreByUser( long userId) {
        Map<String, Object> mapItems = new HashMap<String, Object>();
        List<UserStoreDto> stores = storeService.getStoreByUser(userId);
        mapItems.put("store", stores) ; 
        String response = new ObjectMapper().writeValueAsString(mapItems);
        return new ResponseEntity<>( response , HttpStatus.OK);
    }


}
