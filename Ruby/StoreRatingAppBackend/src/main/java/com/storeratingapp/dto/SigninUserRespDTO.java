package com.storeratingapp.dto;

import org.hibernate.validator.constraints.Length;

import com.storeratingapp.entities.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SigninUserRespDTO {
	
	private Long id;
	private String name;
	private Role role;


}
