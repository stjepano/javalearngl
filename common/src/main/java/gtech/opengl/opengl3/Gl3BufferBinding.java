package gtech.opengl.opengl3;

import gtech.exception.GLException;
import gtech.opengl.BufferBinding;
import gtech.opengl.BufferMapping;
import gtech.opengl.GLMaster;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL15C.*;

class Gl3BufferBinding implements BufferBinding {
    private final GLMaster glMaster;
    private int bufferTarget;
    private int bufferId;

    private BufferMapping currentMapping = null;

    public Gl3BufferBinding(GLMaster glMaster, int bufferTarget, int bufferId) {
        this.glMaster = glMaster;
        this.bufferTarget = bufferTarget;
        this.bufferId = bufferId;
    }

    @Override
    public void upload(float[] data) {
        glBufferSubData(bufferTarget, 0, data);
    }

    @Override
    public void upload(int[] data) {
        glBufferSubData(bufferTarget, 0, data);
    }

    @Override
    public BufferMapping map() {
        ByteBuffer buffer = glMapBuffer(bufferTarget, GL_WRITE_ONLY);
        if (buffer == null) {
            throw new GLException("Could not map buffer.");
        }
        return new Gl3BufferMapping(bufferTarget, buffer);
    }

    @Override
    public void close() {
        glBindBuffer(bufferTarget, 0);
    }

    int getId() {
        return bufferId;
    }

    public static class Gl3BufferMapping implements BufferMapping {
        private final int bufferTarget;
        private final ByteBuffer buffer;

        public Gl3BufferMapping(int bufferTarget, ByteBuffer buffer) {
            this.bufferTarget = bufferTarget;
            this.buffer = buffer;
        }

        @Override
        public ByteBuffer buffer() {
            return buffer;
        }

        @Override
        public void close() {
            glUnmapBuffer(bufferTarget);
        }
    }
}
