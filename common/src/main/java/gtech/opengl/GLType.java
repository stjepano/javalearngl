package gtech.opengl;

import static org.lwjgl.opengl.GL11C.*;

public enum GLType {
    FLOAT(GL_FLOAT, 4),
    UINT(GL_UNSIGNED_INT, 4),
    INT(GL_INT, 4);

    private int glType;
    private int byteSize;

    GLType(int glType, int byteSize) {
        this.glType = glType;
        this.byteSize = byteSize;
    }

    public int size() {
        return byteSize;
    }

    public int getGlType() {
        return glType;
    }
}
