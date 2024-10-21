package com.storeratingapp.dto;

import org.hibernate.validator.constraints.Length;

import com.storeratingapp.entities.Role;
import com.storeratingapp.entities.User;

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
public class UserDTO {
	
	@NotBlank(message = "Name can't be blank!!")
	@Length(min = 3, max = 30, message = "Invalid  name!!")
	@Pattern(regexp = "^[a-z,A-Z  ]{3,30}$")
	private String name;

	@NotBlank(message = "Email can't be blank!!")
	@Email(message = "Invalid email format!!")
	private String email;

	@NotNull(message = "Role must be supplied!!")
	private Role role;

	@NotBlank(message = "Password can't be blank!!")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%?&])[A-Za-z\\d@$!%?&]{8,20}$")
	private String password;

	@NotBlank(message = "Address can't be blank!!")
	private String address;

}
