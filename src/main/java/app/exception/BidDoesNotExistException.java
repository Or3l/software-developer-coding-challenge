package app.exception;

public class BidDoesNotExistException extends RuntimeException {
    public BidDoesNotExistException(String s) {
        super(s);
    }
}
