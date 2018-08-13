package app.exception;

public class AuctionDoesNotExistException extends RuntimeException {
    public AuctionDoesNotExistException(String s) {
        super(s);
    }
}
