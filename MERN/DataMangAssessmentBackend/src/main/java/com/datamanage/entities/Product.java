package com.datamanage.entities;

import java.time.OffsetDateTime;

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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "product")
public class Product extends BaseEntity{
	
	@Length(min = 3, max = 300, message = "invlid title!!")
	@NotNull
	private String title; 

	@NotNull
	private double price;
	
	@Length(min = 3, max =1000)
	private String description;
	
	@Column(length = 20)
	@NotNull
	private String category;
	
	@Length(min = 3, max = 100, message = "invalid image")
	private String image;
	
	@NotNull
	private boolean sold;
	
	@Column(name = "date_of_sale")
	@NotNull
	private OffsetDateTime dateOfSale;
}
