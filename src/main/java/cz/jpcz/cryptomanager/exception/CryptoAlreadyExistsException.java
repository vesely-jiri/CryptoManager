package cz.jpcz.cryptomanager.exception;

public class CryptoAlreadyExistsException extends RuntimeException {
    public CryptoAlreadyExistsException(String message) {
        super(message);
    }
}
