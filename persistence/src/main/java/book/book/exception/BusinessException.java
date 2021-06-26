package book.book.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 2159973574694430047L;

	public BusinessException(final String message) {
		super(message);
	}

}
