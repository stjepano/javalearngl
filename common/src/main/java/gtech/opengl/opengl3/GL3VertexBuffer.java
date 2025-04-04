package gtech.opengl.opengl3;

import gtech.opengl.BufferBinding;
import gtech.opengl.GLMaster;
import gtech.opengl.VertexBuffer;

import static org.lwjgl.opengl.GL15C.*;

class GL3VertexBuffer implements VertexBuffer {
    private final GLMaster glMaster;
    private int id;

    public GL3VertexBuffer(GLMaster glMaster, int id) {
        this.glMaster = glMaster;
        this.id = id;
    }

    @Override
    public BufferBinding bind() {
        glBindBuffer(GL_ARRAY_BUFFER, this.id);
        return new Gl3BufferBinding(glMaster, GL_ARRAY_BUFFER, this.id);
    }

    int getId() {
        return id;
    }

    @Override
    public void close() {
        if (this.id != 0) {
            glDeleteBuffers(id);
            this.id = 0;
        }
    }
}
