package gtech.exception;

import java.io.IOException;

public class ResourceReadException extends RuntimeException {

    public ResourceReadException(String resourcePath, IOException e) {
        super("Failed to read resource: " + resourcePath, e);
    }

}
