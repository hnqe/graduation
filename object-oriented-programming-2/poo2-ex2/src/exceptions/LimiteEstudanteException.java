package exceptions;

public class LimiteEstudanteException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public LimiteEstudanteException(String message) {
		super(message);
	}
}
