package gtech.opengl;

import static org.lwjgl.opengl.GL11C.GL_REPEAT;
import static org.lwjgl.opengl.GL12C.GL_CLAMP_TO_EDGE;
import static org.lwjgl.opengl.GL13C.GL_CLAMP_TO_BORDER;
import static org.lwjgl.opengl.GL14C.GL_MIRRORED_REPEAT;

public enum TextureWrap {
    REPEAT(GL_REPEAT),
    MIRRORED_REPEAT(GL_MIRRORED_REPEAT),
    CLAMP_TO_EDGE(GL_CLAMP_TO_EDGE),
    CLAMP_TO_BORDER(GL_CLAMP_TO_BORDER);

    private final int glEnum;

    TextureWrap(int glEnum) {
        this.glEnum = glEnum;
    }

    public int getGlEnum() {
        return this.glEnum;
    }
}
