package org.jsp.ecommerceapp.repository;

import java.util.Optional;

import org.jsp.ecommerceapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

	public Optional<User> findByEmailAndPassword(String email, String password);

	public Optional<User> findByPhoneAndPassword(long phone, String password);

	public Optional<User> findByToken(String token);
}
