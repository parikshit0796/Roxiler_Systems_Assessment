package com.storeratingapp.services;

import java.util.List;

import com.storeratingapp.dto.SigninUserReqDTO;
import com.storeratingapp.entities.User;

public interface UserService {
	
	public User addUser(User newUser);
	
	public Object authenticate(SigninUserReqDTO request);
	
	public List<User> getAllUsers();
	
	public List<User>getAllOwners();
	
	public User findById(Long id);
	
	public String changePassword(String newPassword, Long userId);

}
