package com.storeratingapp.dto;

import org.hibernate.validator.constraints.Length;

import com.storeratingapp.entities.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO2 {
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String email;

	@NotNull
	private Role role;

	@NotBlank
	private String address;


}
