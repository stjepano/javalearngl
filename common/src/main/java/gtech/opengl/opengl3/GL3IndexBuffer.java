package gtech.opengl.opengl3;

import gtech.opengl.BufferBinding;
import gtech.opengl.GLMaster;
import gtech.opengl.IndexBuffer;

import static org.lwjgl.opengl.GL15C.*;

class GL3IndexBuffer implements IndexBuffer {
    private final GLMaster glMaster;
    private int id;

    public GL3IndexBuffer(GLMaster glMaster, int id) {
        this.glMaster = glMaster;
        this.id = id;
    }

    @Override
    public BufferBinding bind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, this.id);
        return new Gl3BufferBinding(glMaster, GL_ELEMENT_ARRAY_BUFFER, this.id);
    }

    int getId() {
        return id;
    }

    @Override
    public void close() {
        if (this.id != 0) {
            glDeleteBuffers(this.id);
            this.id = 0;
        }
    }
}
