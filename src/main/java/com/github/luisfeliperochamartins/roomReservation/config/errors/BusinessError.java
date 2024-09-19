package com.github.luisfeliperochamartins.roomReservation.config.errors;

public class BusinessError extends RuntimeException {

	public BusinessError(String msg) {
		super(msg);
	}
}
