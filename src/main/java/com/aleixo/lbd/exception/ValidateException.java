package com.aleixo.lbd.exception;

public class ValidateException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidateException(String errorMessage) {
		super(errorMessage);
	}
}
