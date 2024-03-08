package org.jsp.ecommerceapp.service;

import java.util.Optional;

import org.jsp.ecommerceapp.dao.UserDao;
import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.exception.UserNotFoundException;
import org.jsp.ecommerceapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setBody(userDao.saveUser(user));
		structure.setMessage("User saved");
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(User user) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> recUser = userDao.findById(user.getId());
		if (recUser.isPresent()) {
			User dbUser = recUser.get();
			dbUser.setAge(user.getAge());
			dbUser.setGender(user.getGender());
			dbUser.setEmail(user.getEmail());
			dbUser.setPhone(user.getPhone());
			dbUser.setPassword(user.getPassword());
			dbUser.setName(user.getName());
			structure.setBody(userDao.saveUser(dbUser));
			structure.setMessage("user updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
		}
		throw new UserNotFoundException("Invalid User Id");
	}

	public ResponseEntity<ResponseStructure<User>> verifyUser(long phone, String password) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> recUser = userDao.verifyUser(phone, password);
		if (recUser.isPresent()) {
			structure.setBody(recUser.get());
			structure.setMessage("User Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new UserNotFoundException("Invalid Phone Number or password");
	}

	public ResponseEntity<ResponseStructure<User>> verifyUser(String email, String password) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> recUser = userDao.verifyUser(email, password);
		if (recUser.isPresent()) {
			structure.setBody(recUser.get());
			structure.setMessage("User Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new UserNotFoundException("Invalid Phone Number or password");
	}
}
