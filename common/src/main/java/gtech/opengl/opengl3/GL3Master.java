package gtech.opengl.opengl3;

import gtech.data.PixelSurface;
import gtech.exception.GLShaderException;
import gtech.opengl.*;

import static org.lwjgl.opengl.GL11C.*;
import static org.lwjgl.opengl.GL20C.*;
import static org.lwjgl.opengl.GL30C.*;

public class GL3Master implements GLMaster {
    
    private int glSurfaceFormat(PixelSurface.Format format) {
        return switch (format) {
            case RED8 -> GL_RED;
            case RGB8 -> GL_RGB;
            case RGBA8 -> GL_RGBA;
        };
    }

    @Override
    public void viewport(int x, int y, int width, int height) {
        glViewport(x, y, width, height);
    }

    @Override
    public Texture createTexture(PixelSurface surface, TextureFormat targetFormat, boolean generateMipmaps) {
        int textureId = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, textureId);
        glTexImage2D(GL_TEXTURE_2D, 0, targetFormat.getGlFormat(), surface.getWidth(), surface.getHeight(), 0, glSurfaceFormat(surface.getFormat()), GL_UNSIGNED_BYTE,  surface.getPixels());
        if (generateMipmaps) {
            glGenerateMipmap(GL_TEXTURE_2D);
        }
        glBindTexture(GL_TEXTURE_2D, 0);
        return new GL3Texture(this, textureId, surface.getWidth(), surface.getHeight(), targetFormat);
    }

    @Override
    public ShaderProgram createProgram(String vertexShaderSource, String fragmentShaderSource) {
        int vertexShader = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexShader, vertexShaderSource);
        glCompileShader(vertexShader);
        if (glGetShaderi(vertexShader, GL_COMPILE_STATUS) == GL_FALSE) {
            throw new GLShaderException("Vertex shader compilation failed: " + glGetShaderInfoLog(vertexShader));
        }

        int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentShader, fragmentShaderSource);
        glCompileShader(fragmentShader);
        if (glGetShaderi(fragmentShader, GL_COMPILE_STATUS) == GL_FALSE) {
            throw new GLShaderException("Fragment shader compilation failed: " + glGetShaderInfoLog(fragmentShader));
        }

        int programId = glCreateProgram();
        glAttachShader(programId, vertexShader);
        glAttachShader(programId, fragmentShader);
        glLinkProgram(programId);
        if (glGetProgrami(programId, GL_LINK_STATUS) == GL_FALSE) {
            glDeleteShader(vertexShader);
            glDeleteShader(fragmentShader);
            throw new GLShaderException("Shader program linking failed: " + glGetProgramInfoLog(programId));
        }

        glValidateProgram(programId);
        if (glGetProgrami(programId, GL_VALIDATE_STATUS) == GL_FALSE) {
            throw new GLShaderException("Shader program validation failed: " + glGetProgramInfoLog(programId));
        }

        glDeleteShader(vertexShader);
        glDeleteShader(fragmentShader);

        return new GL3ShaderProgram(this, programId);
    }

    @Override
    public VertexBuffer createVertexBuffer(int size, BufferUsage usage) {
        int id = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, id);
        glBufferData(GL_ARRAY_BUFFER, (long) size, usage.toGlEnum());
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        return new GL3VertexBuffer(this, id);
    }

    @Override
    public IndexBuffer createIndexBuffer(GLType type, int count, BufferUsage usage) {
        int id = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, (long) type.size() * count, usage.toGlEnum());
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        return new GL3IndexBuffer(this, id);
    }

    @Override
    public VertexArrayBuilder vertexArrayBuilder() {
        return new GL3VertexArrayBuilder(this);
    }

    @Override
    public void bindTexture(TextureUnit unit, Texture texture, TextureSampler sampler) {
        glActiveTexture(unit.getGLUnit());
        glBindTexture(GL_TEXTURE_2D, ((GL3Texture)texture).getId());
        ((GL3Texture)texture).applySampler(sampler);
    }

    @Override
    public void unbindTexture(TextureUnit unit) {
        glActiveTexture(unit.getGLUnit());
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    @Override
    public void bindProgram(ShaderProgram program) {
        glUseProgram(((GL3ShaderProgram) program).getProgramId());
    }

    @Override
    public void unbindProgram() {
        glUseProgram(0);
    }

    @Override
    public void bindVertexArray(VertexArray vao) {
        glBindVertexArray( ((GL3VertexArray)vao).getId());
    }

    @Override
    public void unbindVertexArray() {
        glBindVertexArray(0);
    }

    @Override
    public void drawIndices(PrimitiveType primitiveType, GLType indexBufferElementType, int count) {
        glDrawElements(primitiveType.getGlEnum(), count, indexBufferElementType.getGlType(), 0L);
    }

    @Override
    public void bindIndexArray(IndexBuffer indexBuffer) {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ((GL3IndexBuffer)indexBuffer).getId());
    }

    @Override
    public void unbindIndexArray() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    @Override
    public void clearColorBuffer(float[] clearColor) {
        glClearBufferfv(GL_COLOR, 0, clearColor);
    }

    @Override
    public void clearDepthBuffer(float val) {
        glClearBufferfv(GL_DEPTH, 0, new float[]{val});
    }

    @Override
    public void enableDepthTest() {
        glEnable(GL_DEPTH_TEST);
    }

    @Override
    public void disableDepthTest() {
        glDisable(GL_DEPTH_TEST);
    }
}
