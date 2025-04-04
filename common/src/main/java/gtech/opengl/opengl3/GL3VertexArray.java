package gtech.opengl.opengl3;

import gtech.opengl.GLMaster;
import gtech.opengl.VertexArray;

import static org.lwjgl.opengl.GL30C.glDeleteVertexArrays;

class GL3VertexArray implements VertexArray {
    private final GLMaster glMaster;
    private int id;

    public GL3VertexArray(GLMaster glMaster, int vaoId) {
        this.glMaster = glMaster;
        this.id = vaoId;
    }

    @Override
    public void close() {
        if (this.id != 0) {
            glDeleteVertexArrays(this.id);
            this.id = 0;
        }
    }

    int getId() {
        return id;
    }


}
