package pl.mmo.repositories;

public class MonetaAlreadyExistsException extends Exception {
    private static final long serialVersionUID = -4576295597218170159L;

    public MonetaAlreadyExistsException() {     
    }

    public MonetaAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public MonetaAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public MonetaAlreadyExistsException(String message) {
        super(message);
    }

    public MonetaAlreadyExistsException(Throwable cause) {
        super(cause);
    }
    
}