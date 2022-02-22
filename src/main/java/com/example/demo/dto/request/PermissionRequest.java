package com.example.demo.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermissionRequest {

    private long id;

    @NotBlank(message = "Please enter a valid permission")
    private String name;

    private String description;
}
