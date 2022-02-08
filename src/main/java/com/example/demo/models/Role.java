package com.example.demo.models;

import javax.persistence.Entity;

import com.example.demo.enums.ERole;

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
@Entity(name = "roles")
public class Role {

	private long id;

	private ERole name;

}
