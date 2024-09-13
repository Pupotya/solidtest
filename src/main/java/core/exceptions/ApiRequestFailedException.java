package core.exceptions;

public class ApiRequestFailedException extends RuntimeException {
    public ApiRequestFailedException(String message) {
        super(message);
    }
}
