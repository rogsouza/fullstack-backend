package com.radsouza.fullstackbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.radsouza.fullstackbackend.exception.UserNotFoundException;
import com.radsouza.fullstackbackend.model.User1;
import com.radsouza.fullstackbackend.repository.UserRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/user")
	User1 newUser(@RequestBody User1 newUser) {
		return userRepository.save(newUser);
	}
	
	@GetMapping("/users")
	List<User1> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/users/{id}")
	User1 getAllUsersById(@PathVariable Long id) {
		return userRepository.findById(id)
				.orElseThrow(()->new UserNotFoundException(id));
	}
	
	@PutMapping("/users/{id}")
	User1 updateUser(@RequestBody User1 newUser, @PathVariable Long id) {
		return userRepository.findById(id)
				.map(user1 -> {
					user1.setUsername(newUser.getUsername());
					user1.setName(newUser.getName());
					user1.setEmail(newUser.getEmail());
					return userRepository.save(user1);
		}).orElseThrow(()->new UserNotFoundException(id));
	}
	
	@DeleteMapping("/users/{id}")
	String deleteUser(@PathVariable Long id) {
		if (!userRepository.existsById(id)) {
			throw new UserNotFoundException(id);
		}
		userRepository.deleteById(id);
		return "User with id " + id + " has been deleted success!";
	}

}
