package com.storeratingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.storeratingapp.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long>{
	
	List<Rating> findByStoreId(Long storeId);

}
