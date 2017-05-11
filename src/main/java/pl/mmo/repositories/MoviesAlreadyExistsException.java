package pl.mmo.repositories;

public class MoviesAlreadyExistsException extends Exception {
    private static final long serialVersionUID = -4576295597218170159L;

    public MoviesAlreadyExistsException() {
    }

    public MoviesAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
                                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public MoviesAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public MoviesAlreadyExistsException(String message) {
        super(message);
    }

    public MoviesAlreadyExistsException(Throwable cause) {
        super(cause);
    }
    
}