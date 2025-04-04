package gtech.exception;

public class GLUniformNotFoundException extends GLException {
    public GLUniformNotFoundException(String uniformName) {
        super("No uniform with name " + uniformName + " found.");
    }
}
