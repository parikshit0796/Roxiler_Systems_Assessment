package com.storeratingapp.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.storeratingapp.dto.SigninUserReqDTO;
import com.storeratingapp.dto.SigninUserRespDTO;
import com.storeratingapp.customException.ResorceNotFoundException;
import com.storeratingapp.entities.Role;
import com.storeratingapp.entities.User;
import com.storeratingapp.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public User addUser(User newUser) {
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		User persistentUser = userRepository.save(newUser);
		return persistentUser;
	}
	
	@Override
	public Object authenticate(SigninUserReqDTO request) {

		List<User> uList = userRepository.getByEmail(request.getEmail());
		User user = null;
		try {
			user = uList.get(0);
		} catch (Exception e) {
			throw new ResorceNotFoundException("Invalid Email!!");
		}

		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new ResorceNotFoundException("Invalid Password!!");
		}
		return new SigninUserRespDTO(user.getId(), user.getName(),user.getRole());
	}

	
	@Override
	public List<User> getAllUsers() {
		List<User> userList = userRepository.findAll();
		return userList;
	}
	
	@Override
	public List<User> getAllOwners() {
		List<User> ownerList = userRepository.findByRole(Role.ROLE_STORE_OWNER);
		return ownerList;
	}
	
	@Override
	 public User findById(Long id) {
	        Optional<User> userOptional = userRepository.findById(id);
	        return userOptional.orElse(null); 
	    }
	
	@Override
	public String changePassword(String newPassword, Long userId) {
		
		Optional<User> optionalUser = userRepository.findById(userId);
		
		if (optionalUser.isPresent()) {
	        User user = optionalUser.get(); 
	        user.setPassword(passwordEncoder.encode(newPassword)); 
	        userRepository.save(user);
	        
	        return "Password changed successfully.";
	    } else {
	        return "User not found. Invalid Email Id";
	    }
	}


	
}
