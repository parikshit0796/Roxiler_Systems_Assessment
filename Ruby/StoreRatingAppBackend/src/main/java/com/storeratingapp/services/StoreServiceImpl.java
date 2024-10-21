package com.storeratingapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storeratingapp.dto.StoreDTO2;
import com.storeratingapp.entities.Rating;
import com.storeratingapp.entities.Store;
import com.storeratingapp.entities.User;
import com.storeratingapp.repositories.RatingRepository;
import com.storeratingapp.repositories.StoreRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private RatingRepository ratingRepository;
	
	@Override
	public Store addStore(Store newStore) {
		Store persistantStore = storeRepository.save(newStore);
		return persistantStore;
	}
	
	@Override
	public List<StoreDTO2> getAllStores() {
        List<Store> stores = storeRepository.findAll();
        List<StoreDTO2> storeDTOs = new ArrayList<>();

        for (Store store : stores) {
            StoreDTO2 dto = new StoreDTO2();
            dto.setId(store.getId());
            dto.setStoreName(store.getStoreName());
            dto.setEmail(store.getEmail());
            dto.setStoreAddress(store.getStoreAddress());
            dto.setStoreOwner(store.getStoreOwner());

            double averageRating = calculateAverageRating(store.getId());
            dto.setAverageRating(averageRating);

            storeDTOs.add(dto);
        }
        return storeDTOs;
    }

    private double calculateAverageRating(Long storeId) {
        List<Rating> ratings = ratingRepository.findByStoreId(storeId);
        if (ratings.isEmpty()) {
            return 0; 
        }
        double sum = ratings.stream().mapToDouble(Rating::getScore).sum();
        return sum / ratings.size(); 
    }
	
	 @Override
	 public void submitRating(Long storeId, Long userId, int score) {
	        Store store = storeRepository.findById(storeId)
	                .orElseThrow(() -> new RuntimeException("Store not found"));
	        User user = new User(); 
	        user.setId(userId); 
	        
	        Rating rating = new Rating();
	        rating.setStore(store);
	        rating.setUser(user);
	        rating.setScore(score);
	        
	        ratingRepository.save(rating);
	    }


	


}
