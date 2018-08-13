package app.exception;

public class CarDoesNotExistException extends RuntimeException {
    public CarDoesNotExistException(String s) {
        super(s);
    }
}
