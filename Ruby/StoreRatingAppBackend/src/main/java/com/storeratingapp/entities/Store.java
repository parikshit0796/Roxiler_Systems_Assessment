package com.storeratingapp.entities;

import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "store")
@ToString
public class Store extends BaseEntity {

	@Column(name = "store_name")
	@Length(min = 3, max = 30, message = "Invalid store name!!")
	@NotNull
	private String storeName;

	@Column(name = "store_email", unique = true)
	@Length(min = 3, max = 30)
	@NotNull
	private String email;

	@Column(name = "store_address")
	@Length(min = 3, max = 300, message = "Invalid store address!!")
	@NotNull
	private String storeAddress;

	@ManyToOne
	@JoinColumn(name = "store_rating_id")
	private Rating storeRating;

	@OneToOne
	@JoinColumn(name = "store_owner_id")
	@NotNull
	private User storeOwner;

	@OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
	private List<Rating> ratings = new ArrayList<>();

}
