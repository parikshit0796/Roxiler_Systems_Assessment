package com.storeratingapp.entities;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "password")
public class User extends BaseEntity{

	@Column(name = "name")
	@Length(min = 3, max = 30, message = "Invalid name!!")
	@NotNull
	private String name;

	@Column(length = 30, unique = true)
	@NotNull
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	@NotNull
	private Role role;

	@Column(length = 300)
	@NotNull
	private String password;

	@Column(length = 300)
	@NotNull
	private String address;
}
