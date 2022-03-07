package com.example.demo.utils;

import com.example.demo.dto.StoreDto;
import com.example.demo.dto.UserStoreDto;
import com.example.demo.models.Store;
import com.example.demo.models.UserStore;

import org.springframework.beans.BeanUtils;

public class EntityMapper {

    // STORE MAPPING //
    public static StoreDto convertToStoreDto(Store store) {
        StoreDto storeDto = new StoreDto();
        BeanUtils.copyProperties(store, storeDto);
        return storeDto;
    }

    public static Store convertToStore(StoreDto storeDto) {
        Store store = new Store();
        BeanUtils.copyProperties(storeDto, store);
        return store;
    }

    public static UserStoreDto convertToUserStoreDto(UserStore userStore) {
        UserStoreDto userStoreDto = new UserStoreDto();
        BeanUtils.copyProperties(userStore, userStoreDto);
        return userStoreDto;
    }

    // -- MAPPING //
}
