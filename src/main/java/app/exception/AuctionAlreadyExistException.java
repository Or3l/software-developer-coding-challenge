package app.exception;

public class AuctionAlreadyExistException extends RuntimeException {
    public AuctionAlreadyExistException(String s) {
        super(s);
    }
}
