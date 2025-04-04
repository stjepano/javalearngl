package gtech.util;

import gtech.exception.ResourceNotFoundException;
import gtech.exception.ResourceReadException;

import java.io.IOException;
import java.io.InputStream;

public final class Resources {
    
    public static String readText(String resourcePath) {
        try (InputStream in = Resources.class.getResourceAsStream(resourcePath)) {
            if (in == null) {
                throw new ResourceNotFoundException(resourcePath);
            }

            final StringBuilder sb = new StringBuilder();
            try (var reader = new java.io.BufferedReader(new java.io.InputStreamReader(in))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            }
            return sb.toString();
        } catch (IOException e) {
            throw new ResourceReadException(resourcePath, e);
        }
    }
}
