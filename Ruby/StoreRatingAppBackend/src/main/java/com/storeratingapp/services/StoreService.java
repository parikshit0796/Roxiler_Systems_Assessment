package com.storeratingapp.services;

import com.storeratingapp.dto.StoreDTO2;
import com.storeratingapp.entities.Store;
import java.util.List;

public interface StoreService {
	
	public Store addStore(Store newStore); 
	
	public List<StoreDTO2> getAllStores();
	
	 public void submitRating(Long storeId, Long userId, int score);

}
