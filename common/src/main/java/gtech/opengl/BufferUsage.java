package gtech.opengl;

import static org.lwjgl.opengl.GL15C.*;

public enum BufferUsage {
    /** Modified once and used many times. Use for drawing */
    STATIC_DRAW(GL_STATIC_DRAW),
    /** Modified once and used many times. Used to return data. */
    STATIC_READ(GL_STATIC_READ),
    /** Modified once and used many times. Use as source for GL drawing. */
    STATIC_COPY(GL_STATIC_COPY),

    /** Modified repeatedly and used many times. Use for drawing */
    DYNAMIC_DRAW(GL_DYNAMIC_DRAW),
    /** Modified repeatedly and used many times. Used to return data. */
    DYNAMIC_READ(GL_DYNAMIC_READ),
    /** Modified repeatedly and used many times. Use as source for GL drawing. */
    DYNAMIC_COPY(GL_DYNAMIC_COPY),

    /** Modified once and used at most few times. Use for drawing */
    STREAM_DRAW(GL_STREAM_DRAW),
    /** Modified once and used at most few times. Used to return data. */
    STREAM_READ(GL_STREAM_READ),
    /** Modified once and used at most few times. Use as source for GL drawing. */
    STREAM_COPY(GL_STREAM_COPY);

    private int glEnum;

    BufferUsage(int glEnum) {
        this.glEnum = glEnum;
    }

    public int toGlEnum() {
        return glEnum;
    }
}
