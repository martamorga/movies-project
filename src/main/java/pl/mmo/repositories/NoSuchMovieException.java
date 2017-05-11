package pl.mmo.repositories;

public class NoSuchMovieException extends Exception {
    private static final long serialVersionUID = -8555511053844242536L;

    public NoSuchMovieException(String string) {
		super(string);
	}

	public NoSuchMovieException() {
	}


}