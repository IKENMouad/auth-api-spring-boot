package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserStoreDto {

    private Long id;

    private String name;

    private String description;

    private String address;

    private boolean enable;

    private boolean hasAccess;
}
