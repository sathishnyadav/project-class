package org.jsp.ecommerceapp.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, unique = true)
	private long phone;
	@Column(nullable = false)
	private int age;
	@Column(nullable = false)
	private String gender;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String password;
	private String status;
	private String token;
	@OneToMany(mappedBy = "user")
	private List<Address> addresses;
	@JoinTable(name = "user_cart", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "product_id") })
	@OneToMany
	private List<Product> cart;
	@JoinTable(name = "user_wishlist", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "product_id") })
	@OneToMany
	private List<Product> wishList;
	@OneToMany(mappedBy = "user")
	private List<UserOrder> orders;
}
