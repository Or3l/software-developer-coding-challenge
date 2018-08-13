package app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<AuctionErrorResponse> handleException(UserDoesNotExistException exception) {
		AuctionErrorResponse topicErrorResponse = new AuctionErrorResponse(
				exception.getMessage());
		return new ResponseEntity<AuctionErrorResponse>(topicErrorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<AuctionErrorResponse> handleException(Exception exception) {
		AuctionErrorResponse topicErrorResponse = new AuctionErrorResponse(HttpStatus.BAD_REQUEST.toString(),
				exception.getMessage());
		return new ResponseEntity<AuctionErrorResponse>(topicErrorResponse, HttpStatus.BAD_REQUEST);
	}

}
