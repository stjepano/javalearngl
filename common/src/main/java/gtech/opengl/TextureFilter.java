package gtech.opengl;

import static org.lwjgl.opengl.GL11C.GL_LINEAR;
import static org.lwjgl.opengl.GL11C.GL_NEAREST;

public enum TextureFilter {
    NEAREST(GL_NEAREST),
    LINEAR(GL_LINEAR);

    private final int glEnum;

    TextureFilter(int glEnum) {
        this.glEnum = glEnum;
    }

    public int getGlEnum() {
        return this.glEnum;
    }
}
