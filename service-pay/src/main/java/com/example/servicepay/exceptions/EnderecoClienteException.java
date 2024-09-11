package com.example.servicepay.exceptions;

public class EnderecoClienteException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public EnderecoClienteException(String errorMessage) {
		super(errorMessage);
	}

}
