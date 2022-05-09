package com.chocoapp.exception;

public class DBException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DBException(String message, Throwable cause) {
		super(message, cause);

	}

	public DBException(String message) {
		super(message);

	}

	public DBException(Throwable cause) {
		super(cause);

	}

}
