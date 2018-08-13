package app.exception;

public class AuctionAlreadyExistException extends Exception {
    public AuctionAlreadyExistException(String s) {
        super(s);
    }
}
