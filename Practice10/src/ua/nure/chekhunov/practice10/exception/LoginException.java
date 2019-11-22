package ua.nure.chekhunov.practice10.exception;

public class LoginException extends RuntimeException {

	public LoginException() {

	}

	public LoginException(String message) {
		super(message);
	}

	public LoginException(Throwable cause) {
		super(cause);
	}

	public LoginException(String message, Throwable cause) {
		super(message, cause);
	}

}
