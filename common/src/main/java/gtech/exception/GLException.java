package gtech.exception;

public class GLException extends RuntimeException {
    public GLException() {
    }

    public GLException(String message) {
        super(message);
    }

    public GLException(String message, Throwable cause) {
        super(message, cause);
    }

    public GLException(Throwable cause) {
        super(cause);
    }
}
