package edu.ubb.cs.repository;

public class DatabaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DatabaseException() {
		super();
	}
	
	public DatabaseException(final String message) {
		super(message);
	}
	
	public DatabaseException(final String message, final Throwable cause) {
		super(message, cause);
	}
}