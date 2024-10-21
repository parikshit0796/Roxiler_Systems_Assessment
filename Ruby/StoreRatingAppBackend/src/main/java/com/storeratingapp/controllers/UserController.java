package com.storeratingapp.controllers;

import org.modelmapper.ModelMapper;
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
import org.springframework.web.bind.annotation.PostMapping;

import com.storeratingapp.dto.SigninUserReqDTO;
import com.storeratingapp.dto.UserDTO;
import com.storeratingapp.entities.User;
import com.storeratingapp.services.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper mapper;
	
	@PostMapping("/signup")
	public ResponseEntity <?> signUp(@RequestBody @Valid UserDTO newUserDTO) {
		User newUser = mapper.map(newUserDTO, User.class);
		return new ResponseEntity<>(userService.addUser(newUser), HttpStatus.OK);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> login(@RequestBody @Valid SigninUserReqDTO request) {
		return new ResponseEntity<>(userService.authenticate(request), HttpStatus.OK);
	}
	
	@GetMapping("/getallusers")
	public ResponseEntity<?> usersList(){
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}
	
	@GetMapping("/getallstoreowners")
	public ResponseEntity<?> ownerList(){
		return new ResponseEntity<>(userService.getAllOwners(), HttpStatus.OK);
	}
	
	@PostMapping("/changepassword/{newPassword}")
	public ResponseEntity<?> changePassword(@PathVariable String newPassword, @RequestBody Long userId){
		return new ResponseEntity<>(userService.changePassword(newPassword, userId), HttpStatus.OK);
	}

}
