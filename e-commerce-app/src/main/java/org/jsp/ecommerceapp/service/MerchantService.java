package org.jsp.ecommerceapp.service;

import java.util.Optional;

import org.jsp.ecommerceapp.dao.MerchantDao;
import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.exception.MerchantNotFoundException;
import org.jsp.ecommerceapp.model.Merchant;
import org.jsp.ecommerceapp.util.AccountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;

@Service
public class MerchantService {
	@Autowired
	private MerchantDao merchantDao;
	@Autowired
	private ECommerceAppEmailService emailService;

	public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(Merchant merchant, HttpServletRequest request) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		merchant.setStatus(AccountStatus.IN_ACTIVE.toString());
		merchant.setToken(RandomString.make(45));
		structure.setBody(merchantDao.saveMerchant(merchant));
		String message = emailService.sendWelcomeMail(merchant, request);
		structure.setMessage("Merchant saved" + "," + message);
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(Merchant merchant) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantDao.findById(merchant.getId());
		if (recMerchant.isPresent()) {
			Merchant dbMerchant = recMerchant.get();
			dbMerchant.setEmail(merchant.getEmail());
			dbMerchant.setGst_number(merchant.getGst_number());
			dbMerchant.setPhone(merchant.getPhone());
			dbMerchant.setPassword(merchant.getPassword());
			dbMerchant.setName(merchant.getName());
			structure.setBody(merchantDao.saveMerchant(dbMerchant));
			structure.setMessage("merchant updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.ACCEPTED);
		}
		throw new MerchantNotFoundException("Invalid Merchant Id");
	}

	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(long phone, String password) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantDao.verifyMerchant(phone, password);
		if (recMerchant.isPresent()) {
			Merchant merchant = recMerchant.get();
			if (merchant.getStatus().equals(AccountStatus.IN_ACTIVE.toString())) {
				throw new IllegalStateException("Account is Not Activated");
			}
			structure.setBody(merchant);
			structure.setMessage("Merchant Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
		}
		throw new MerchantNotFoundException("Invalid Phone Number or password");
	}

	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(String email, String password) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantDao.verifyMerchant(email, password);
		if (recMerchant.isPresent()) {
			Merchant merchant = recMerchant.get();
			if (merchant.getStatus().equals(AccountStatus.IN_ACTIVE.toString())) {
				throw new IllegalStateException("Account is Not Activated");
			}
			structure.setBody(merchant);
			structure.setMessage("Merchant Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
		}
		throw new MerchantNotFoundException("Invalid Email Id or password");
	}

	public ResponseEntity<ResponseStructure<String>> activate(String token) {
		Optional<Merchant> recMerchant = merchantDao.findByToken(token);
		ResponseStructure<String> structure = new ResponseStructure<>();
		if (recMerchant.isPresent()) {
			Merchant merchant = recMerchant.get();
			merchant.setStatus(AccountStatus.ACTIVE.toString());
			merchant.setToken(null);
			merchantDao.saveMerchant(merchant);
			structure.setBody("Merchant Found");
			structure.setMessage("Account Verified and Activated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.ACCEPTED);

		}
		throw new MerchantNotFoundException("Invalid URL");
	}
}
