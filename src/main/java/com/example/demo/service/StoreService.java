package com.example.demo.service;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.dto.StoreDto;
import com.example.demo.dto.UserStoreDto;

public interface StoreService {

    StoreDto createStore(StoreDto storeDto);

    StoreDto getStore(long storeId);

    List<UserStoreDto> getStoreByUser(long userId);

    StoreDto updateStore(long storeId, @Valid StoreDto storeDto);
}
