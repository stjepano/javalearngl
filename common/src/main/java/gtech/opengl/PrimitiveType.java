package gtech.opengl;

import static org.lwjgl.opengl.GL11C.*;

public enum PrimitiveType {
    POINTS(GL_POINTS),
    LINE_STRIP(GL_LINE_STRIP),
    LINE_LOOP(GL_LINE_LOOP),
    LINES(GL_LINES),
    TRIANGLE_STRIP(GL_TRIANGLE_STRIP),
    TRIANGLE_FAN(GL_TRIANGLE_FAN),
    TRIANGLES(GL_TRIANGLES);

    private final int glEnum;

    PrimitiveType(int glEnum) {
        this.glEnum = glEnum;
    }

    public int getGlEnum() {
        return glEnum;
    }
}
