package com.storeratingapp.dto;

import org.hibernate.validator.constraints.Length;

import com.storeratingapp.entities.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SigninUserReqDTO {

	@NotBlank(message = "Email can't be blank")
	@Email(message = "Invalid email format")
	private String email;
	@Length(min = 5,max=20,message = "Invalid password length")
	private String password;
}
