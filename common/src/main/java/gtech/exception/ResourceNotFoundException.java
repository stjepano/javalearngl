package gtech.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourcePath) {
        super("Resource not found: " + resourcePath);
    }
}
