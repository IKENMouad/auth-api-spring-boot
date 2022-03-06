package com.example.demo.repository;

import com.example.demo.models.Store;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository  extends JpaRepository<Store , Long>  {
     
}
