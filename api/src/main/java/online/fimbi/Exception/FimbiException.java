package online.fimbi.Exception;

public class FimbiException extends RuntimeException {
	public FimbiException(String message) {
		super(message);
	}

	public FimbiException(String message, Throwable cause) {
		super(message, cause);
	}

}
