package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.*;

@Entity(name = "permissions")
@Getter
@Setter
@AllArgsConstructor 
@ToString
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please enter a valid permission ")
    private String name;

    private String description;

   
    public Permission(@NotBlank(message = "Please enter a valid permission ") String name, String description) {
        this.name = name;
        this.description = description;
    }
    public Permission( ) { 
    }
}
