package app.exception;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class ErrorResponse {

    @JsonIgnore
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

    public ErrorResponse(String message) {
        this.cause = message;
    }

    public ErrorResponse(String error, String cause) {
        super();
        this.error = error;
        this.cause = cause;
    }


}
