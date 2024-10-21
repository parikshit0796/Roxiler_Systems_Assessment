package com.storeratingapp.customException;

public class ResorceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ResorceNotFoundException(String mesg) {
		super(mesg);
	}


}
