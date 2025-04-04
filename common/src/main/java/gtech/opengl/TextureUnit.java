package gtech.opengl;

import static org.lwjgl.opengl.GL13C.*;

public enum TextureUnit {
    UNIT0(GL_TEXTURE0),
    UNIT1(GL_TEXTURE1),
    UNIT2(GL_TEXTURE2),
    UNIT3(GL_TEXTURE3),
    UNIT4(GL_TEXTURE4),
    UNIT5(GL_TEXTURE5),
    UNIT6(GL_TEXTURE6),
    UNIT7(GL_TEXTURE7);

    private final int glUnit;

    TextureUnit(int glUnit) {
        this.glUnit = glUnit;
    }

    public int getGLUnit() {
        return glUnit;
    }
}
