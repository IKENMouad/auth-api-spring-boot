package com.example.demo.repository;

import com.example.demo.models.Store;
import com.example.demo.models.User;
import com.example.demo.models.UserStore;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStoreRepository extends JpaRepository<UserStore, Long> {
 

    UserStore getByUserAndStore(User user, Store store);
    

}
