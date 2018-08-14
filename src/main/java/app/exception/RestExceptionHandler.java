package app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(UserDoesNotExistException exception) {
		ErrorResponse errorResponse = new ErrorResponse(
				exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}


	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(AuctionDoesNotExistException exception) {
		ErrorResponse errorResponse = new ErrorResponse(
				exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(AuctionAlreadyExistException exception) {
		ErrorResponse errorResponse = new ErrorResponse(
				exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}


	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(CarDoesNotExistException exception) {
		ErrorResponse errorResponse = new ErrorResponse(
				exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}


	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(BidDoesNotExistException exception) {
		ErrorResponse errorResponse = new ErrorResponse(
				exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}


	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(Exception exception) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.toString(),
				exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
