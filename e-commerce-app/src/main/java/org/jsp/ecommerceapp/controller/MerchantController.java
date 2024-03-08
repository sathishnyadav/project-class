package org.jsp.ecommerceapp.controller;

import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.model.Merchant;
import org.jsp.ecommerceapp.service.MerchantService;
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
@RequestMapping("/merchants")
@CrossOrigin
public class MerchantController {
	@Autowired
	private MerchantService merchantService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(@RequestBody Merchant merchant) {
		return merchantService.saveMerchant(merchant);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(@RequestBody Merchant merchant) {
		return merchantService.updateMerchant(merchant);
	}

	@PostMapping("/verify-by-phone")
	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(@RequestParam(name = "phone") long phone,
			@RequestParam(name = "password") String password) {
		return merchantService.verifyMerchant(phone, password);
	}

	@PostMapping("/verify-by-email")
	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(@RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password) {
		return merchantService.verifyMerchant(email, password);
	}
}
