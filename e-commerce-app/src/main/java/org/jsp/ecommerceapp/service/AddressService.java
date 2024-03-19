package org.jsp.ecommerceapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerceapp.dao.AddressDao;
import org.jsp.ecommerceapp.dao.UserDao;
import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.exception.AddressNotFoundException;
import org.jsp.ecommerceapp.exception.UserNotFoundException;
import org.jsp.ecommerceapp.model.Address;
import org.jsp.ecommerceapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<Address>> findById(int id) {
		ResponseStructure<Address> structure = new ResponseStructure<>();
		Optional<Address> recAddress = addressDao.findById(id);
		if (recAddress.isEmpty()) {
			throw new AddressNotFoundException("Invalid Address Id");
		}
		structure.setBody(recAddress.get());
		structure.setMessage("Address Found");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Address>> saveAddress(Address address, int user_id) {
		Optional<User> recMechant = userDao.findById(user_id);
		ResponseStructure<Address> structure = new ResponseStructure<>();
		if (recMechant.isPresent()) {
			User user = recMechant.get();
			user.getAddresses().add(address);
			address.setUser(user);
			structure.setBody(addressDao.saveAddress(address));
			structure.setMessage("Address Added");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.CREATED);
		}
		throw new UserNotFoundException("cannot add Address as User Id is Invalid");
	}

	public ResponseEntity<ResponseStructure<Address>> updateAddress(Address address) {
		ResponseStructure<Address> structure = new ResponseStructure<>();
		Optional<Address> recAddress = addressDao.findById(address.getId());
		if (recAddress.isEmpty()) {
			throw new AddressNotFoundException("cannot update address as Id is Invalid");
		}
		Address dbAddress = recAddress.get();
		dbAddress.setAddress_type(address.getAddress_type());
		dbAddress.setCity(address.getCity());
		dbAddress.setBuildingName(address.getBuildingName());
		dbAddress.setHouse_number(address.getHouse_number());
		dbAddress.setCountry(address.getCountry());
		dbAddress.setPincode(address.getPincode());
		dbAddress.setLandMark(address.getLandMark());
		structure.setBody(addressDao.saveAddress(dbAddress));
		structure.setMessage("Address Updated");
		structure.setStatusCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.ACCEPTED);

	}

	public ResponseEntity<ResponseStructure<List<Address>>> findAddresssByUserId(int user_id) {
		ResponseStructure<List<Address>> structure = new ResponseStructure<>();
		List<Address> addresss = addressDao.findAddressByUserId(user_id);
		if (addresss.isEmpty()) {
			throw new AddressNotFoundException("No Addresss Found for entered User Id");
		}
		structure.setBody(addresss);
		structure.setMessage("List of Addresss for User Id");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Address>>>(structure, HttpStatus.OK);
	}

}
