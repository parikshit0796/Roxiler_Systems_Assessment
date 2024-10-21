package com.storeratingapp.dto;

import com.storeratingapp.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StoreDTO2 {
	
	private Long id;
    private String storeName;
    private String email;
    private String storeAddress;
    private double averageRating;
    private User storeOwner;

}
