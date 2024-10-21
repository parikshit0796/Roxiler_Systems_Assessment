package com.storeratingapp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storeratingapp.entities.Role;
import com.storeratingapp.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByEmail(String email);
	
	List<User> getByEmail(String email);
	
	List<User> findByRole(Role role);

}
