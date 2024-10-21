package com.storeratingapp.dto;

import org.hibernate.validator.constraints.Length;

import com.storeratingapp.entities.Rating;
import com.storeratingapp.entities.User;

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
public class StoreDTO {
	
	@NotBlank(message = "name can't be blank!!")
	@Length(min = 3, max = 30, message = "Invalid name!!")
	@Pattern(regexp = "^[a-z,A-Z, ]{3,30}$")
	private String storeName;
	
	@NotNull
	private String email;
	
	@NotNull
	private String storeAddress;
	
	@NotNull(message = "storeOwnerId must not be null")
	private Long storeOwnerId;
	

}
