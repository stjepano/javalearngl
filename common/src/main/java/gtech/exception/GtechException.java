package gtech.exception;

public class GtechException extends RuntimeException {

    public GtechException() {
    }

    public GtechException(String message) {
        super(message);
    }

    public GtechException(String message, Throwable cause) {
        super(message, cause);
    }

    public GtechException(Throwable cause) {
        super(cause);
    }

}
