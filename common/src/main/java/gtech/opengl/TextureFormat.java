package gtech.opengl;

import static org.lwjgl.opengl.GL11C.GL_RGB8;
import static org.lwjgl.opengl.GL11C.GL_RGBA8;
import static org.lwjgl.opengl.GL30C.GL_R8;

public enum TextureFormat {
    /** 4 channels: red, green, blue and alpha. Each 8bit */
    RGBA8(GL_RGBA8),
    /** 3 channels: red, green and blue. Each 8bit */
    RGB8(GL_RGB8),
    /** One channel, 8 bit */
    R8(GL_R8);

    private int glFormat;

    TextureFormat(int glFormat) {
        this.glFormat = glFormat;
    }

    public int getGlFormat() {
        return glFormat;
    }
}
