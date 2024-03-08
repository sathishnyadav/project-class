package org.jsp.ecommerceapp.controller;

import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.model.User;
import org.jsp.ecommerceapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}

	@PostMapping("/verify-by-phone")
	public ResponseEntity<ResponseStructure<User>> verifyUser(@RequestParam(name = "phone") long phone,
			@RequestParam(name = "password") String password) {
		return userService.verifyUser(phone, password);
	}

	@PostMapping("/verify-by-email")
	public ResponseEntity<ResponseStructure<User>> verifyUser(@RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password) {
		return userService.verifyUser(email, password);
	}
}
