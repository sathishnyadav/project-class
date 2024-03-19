package org.jsp.ecommerceapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerceapp.model.Address;
import org.jsp.ecommerceapp.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDao {
	@Autowired
	private AddressRepository addressRepository;

	public Address saveAddress(Address address) {
		return addressRepository.save(address);
	}

	public Optional<Address> findById(int id) {
		return addressRepository.findById(id);
	}

	public List<Address> findAddressByUserId(int id) {
		return addressRepository.findByUserId(id);
	}
}
