package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private long  id ;

    private String  email ;

    private String name  ;

    private Set<String> roles ;

    private String message  ;

    public RegisterResponse(String message) {
        this.message = message;
    }
}
