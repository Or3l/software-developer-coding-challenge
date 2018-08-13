package app.exception;




public class AuctionErrorResponse {


	private String error;
	private String cause;
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}

	public AuctionErrorResponse(String message){
		this.cause = message;
	}
	public AuctionErrorResponse(String error, String cause) {
		super();
		this.error = error;
		this.cause = cause;
	}
	
	
	

}
