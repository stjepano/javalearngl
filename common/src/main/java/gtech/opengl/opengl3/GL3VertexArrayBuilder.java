package gtech.opengl.opengl3;

import gtech.opengl.*;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL15C.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15C.glBindBuffer;
import static org.lwjgl.opengl.GL20C.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20C.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30C.glBindVertexArray;
import static org.lwjgl.opengl.GL30C.glGenVertexArrays;

class GL3VertexArrayBuilder implements VertexArrayBuilder {

    static class VertexAttribBinding {
        VertexBuffer buffer;
        int layoutLocation;
        int count;
        GLType type;
        int stride;
        int offset;
    }

    private VertexBuffer currentBuffer;
    private final List<VertexAttribBinding> bindings = new ArrayList<>();

    private final GLMaster glMaster;

    GL3VertexArrayBuilder(GLMaster glMaster) {
        this.glMaster = glMaster;
    }

    @Override
    public VertexArrayBuilder fromBuffer(VertexBuffer buffer) {
        this.currentBuffer = buffer;
        return this;
    }

    @Override
    public VertexArrayBuilder bindAttribute(int layoutLocation, GLType type, int count, int stride, int offset) {
        VertexAttribBinding binding = new VertexAttribBinding();
        if (currentBuffer == null) {
            throw new IllegalStateException("No buffer");
        }
        binding.buffer = currentBuffer;
        binding.layoutLocation = layoutLocation;
        binding.count = count;
        binding.type = type;
        binding.stride = stride;
        binding.offset = offset;
        bindings.add(binding);
        return this;
    }

    @Override
    public VertexArray build() {
        bindings.sort((a, b) -> {
            GL3VertexBuffer aBuffer = (GL3VertexBuffer) a.buffer;
            GL3VertexBuffer bBuffer = (GL3VertexBuffer) b.buffer;
            int bufferCompare = Integer.compare(aBuffer.getId(), bBuffer.getId());
            if (bufferCompare != 0) {
                return bufferCompare;
            }

            return Integer.compare(a.layoutLocation, b.layoutLocation);
        });
        int vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);

        int currentBufferId = -1;
        for (VertexAttribBinding binding : bindings) {
            GL3VertexBuffer vertexBuffer = (GL3VertexBuffer) binding.buffer;
            if (vertexBuffer.getId() != currentBufferId) {
                glBindBuffer(GL_ARRAY_BUFFER, vertexBuffer.getId());
                currentBufferId = vertexBuffer.getId();
            }

            glVertexAttribPointer(binding.layoutLocation, binding.count, binding.type.getGlType(), false, binding.stride, binding.offset);
            glEnableVertexAttribArray(binding.layoutLocation);
        }


        glBindVertexArray(0);
        return new GL3VertexArray(glMaster, vaoId);
    }
}
