package org.jsp.ecommerceapp.exception;

public class MerchantNotFoundException extends RuntimeException {
	public MerchantNotFoundException(String message) {
		super(message);
	}
}
