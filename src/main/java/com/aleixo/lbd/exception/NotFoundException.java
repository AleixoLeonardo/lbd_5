package com.aleixo.lbd.exception;

public class NotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}
