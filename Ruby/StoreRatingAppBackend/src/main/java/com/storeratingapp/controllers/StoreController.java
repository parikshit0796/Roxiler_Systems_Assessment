package com.storeratingapp.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.storeratingapp.dto.RatingRequest;
import com.storeratingapp.dto.StoreDTO;
import com.storeratingapp.dto.StoreDTO2;
import com.storeratingapp.entities.Store;
import com.storeratingapp.entities.User;
import com.storeratingapp.repositories.StoreRepository;
import com.storeratingapp.services.StoreService;
import com.storeratingapp.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/store")
@CrossOrigin(origins = "http://localhost:3000")
public class StoreController {
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@PostMapping("/addstore")
	public ResponseEntity<?> addNewStore(@RequestBody StoreDTO newStoreDTO ){
	    User storeOwner = userService.findById(newStoreDTO.getStoreOwnerId());
	    Store newStore = mapper.map(newStoreDTO, Store.class);
	    newStore.setStoreOwner(storeOwner);
		return new ResponseEntity<>(storeService.addStore(newStore), HttpStatus.OK);
	}

	 @PostMapping("/submitrating/{storeId}")
	    public ResponseEntity<?> rateStore(@PathVariable Long storeId, @RequestBody RatingRequest ratingRequest) {
	        Long userId = ratingRequest.getUserId(); 

	        storeService.submitRating(storeId, userId, ratingRequest.getScore());

	        return ResponseEntity.ok("Rating submitted successfully!");
	    }
	 
	 @GetMapping("/getallstores")
	    public ResponseEntity<List<StoreDTO2>> getAllStores() {
	        List<StoreDTO2> stores = storeService.getAllStores();
	        return ResponseEntity.ok(stores);
	    }
	 
}
