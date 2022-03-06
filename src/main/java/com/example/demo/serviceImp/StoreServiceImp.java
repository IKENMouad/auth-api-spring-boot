package com.example.demo.serviceImp;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.demo.dto.StoreDto;
import com.example.demo.dto.UserStoreDto;
import com.example.demo.models.Store;
import com.example.demo.models.User;
import com.example.demo.models.UserStore;
import com.example.demo.repository.StoreRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserStoreRepository;
import com.example.demo.service.StoreService;
import com.example.demo.utils.EntityMapper;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreServiceImp implements StoreService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final UserStoreRepository userStoreRepository;
   
    @Override
    public StoreDto createStore( StoreDto storeDto ) {
        Store store = EntityMapper.convertToStore(storeDto);
        return EntityMapper.convertToStoreDto(storeRepository.save(store));
    }
    
    @Override
    public StoreDto updateStore(long storeId, @Valid StoreDto storeDto) {
        Store store = EntityMapper.convertToStore(storeDto);
        if ( storeRepository.getById( storeId ) == null) {
            throw new NoSuchElementException( "store not found " + storeId );
        }
        store.setId(storeId); 
        return EntityMapper.convertToStoreDto(storeRepository.save(store));
    }


    @Override
    public StoreDto getStore(long storeId) {
        Store store = storeRepository.getById(storeId);
        return EntityMapper.convertToStoreDto(store);
    }


    @Override
    public List<UserStoreDto> getStoreByUser(long userId) {
        User user = userRepository.getById(userId);
        List<UserStore> userStores = userStoreRepository.getByUser(user);
        List<UserStoreDto> stores = userStores.stream().map(val -> EntityMapper.convertToUserStoreDto(val))
                .collect(Collectors.toList());
        return stores;
    }

}
