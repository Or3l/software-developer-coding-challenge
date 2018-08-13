package app.exception;

public class AuctionDoesNotExistException extends Exception {
    public AuctionDoesNotExistException(String s) {
        super(s);
    }
}
