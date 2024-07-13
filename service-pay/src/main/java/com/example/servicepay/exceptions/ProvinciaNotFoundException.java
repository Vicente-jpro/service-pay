package com.example.servicepay.exceptions;

public class ProvinciaNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProvinciaNotFoundException (String errorMessage) {
		super(errorMessage);
	}
}
