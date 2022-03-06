package com.example.demo.serviceImp;

import java.util.List;

import com.example.demo.dto.StoreDto;
import com.example.demo.models.Store;
import com.example.demo.models.User;
import com.example.demo.models.UserStore;
import com.example.demo.repository.StoreRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserStoreRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceApi {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final UserStoreRepository userStoreRepository;

    public User getUserById(long id) {
        return userRepository.findById(id).get();
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public String attachStoreToUser(long userId, StoreDto storeDto) {
        User user = userRepository.getById(userId);
        Store store = storeRepository.getById(storeDto.getId());
        if (user == null || store == null) {
            return "error: user or store not found";
        }
        UserStore fetchedUserStore = userStoreRepository.getByUserAndStore(user, store);
        if (fetchedUserStore != null) {
            return "user already have this store";

        }
        UserStore userStore = new UserStore(null, user, store, true);

        userStore = userStoreRepository.save(userStore);

        return (userStore != null && userStore.getId() != null) ? "success" : "error";
    }

    public String dettachStoreFromUser(long userId, StoreDto storeDto) {
        User user = userRepository.getById(userId);
        Store store = storeRepository.getById(storeDto.getId());
        if (user == null || store == null) {
            return "error: user or store not found";
        }
        UserStore fetchedUserStore = userStoreRepository.getByUserAndStore(user, store);
        if (fetchedUserStore == null) {
            return "user doesnt have this store";
        } 
         userStoreRepository.delete(fetchedUserStore);

        return  "success";
    }

    public String enableOrDisableUserFromUser(long userId, StoreDto storeDto) {
        User user = userRepository.getById(userId);
        Store store = storeRepository.getById(storeDto.getId());
        if (user == null || store == null) {
            return "error: user or store not found";
        }
        UserStore fetchedUserStore = userStoreRepository.getByUserAndStore(user, store);
        if (fetchedUserStore == null) {
            return "user doesnt have this store";
        } 
        fetchedUserStore.setEnable(!fetchedUserStore.isEnable());
         userStoreRepository.save(fetchedUserStore);

        return  "success";
    }
}
