package org.jsp.ecommerceapp.dao;

import java.util.Optional;

import org.jsp.ecommerceapp.model.User;
import org.jsp.ecommerceapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	private UserRepository userRepository;

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public Optional<User> findByToken(String token) {
		return userRepository.findByToken(token);
	}

	public Optional<User> verifyUser(long phone, String password) {
		return userRepository.findByPhoneAndPassword(phone, password);
	}

	public Optional<User> verifyUser(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}

	public Optional<User> findById(int id) {
		return userRepository.findById(id);
	}
}
