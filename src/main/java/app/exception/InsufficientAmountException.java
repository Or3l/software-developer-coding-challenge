package app.exception;

public class InsufficientAmountException extends RuntimeException {

    public InsufficientAmountException(String s) {
        super(s);
    }
}
