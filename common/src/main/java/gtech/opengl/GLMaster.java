package gtech.opengl;

import gtech.data.PixelSurface;

public interface GLMaster {

    /** Set opengl viewport */
    void viewport(int x, int y, int width, int height);

    /** Load a texture from file */
    Texture createTexture(PixelSurface surface, TextureFormat targetFormat, boolean generateMipmaps);

    /** Compile and link vertex shader and fragment shader into single program */
    ShaderProgram createProgram(String vertexShaderSource, String fragmentShaderSource);

    /** Create a vertex buffer with specified size and usage. */
    VertexBuffer createVertexBuffer(int size, BufferUsage usage);

    /** Create an index buffer with elements of specified type, reserves space for count of them. Usage */
    IndexBuffer createIndexBuffer(GLType type, int count, BufferUsage usage);

    /** Create a vertex array builder. */
    VertexArrayBuilder vertexArrayBuilder();

    void bindTexture(TextureUnit unit, Texture texture, TextureSampler sampler);
    void unbindTexture(TextureUnit unit);

    void bindProgram(ShaderProgram program);
    void unbindProgram();

    void bindVertexArray(VertexArray vao);
    void unbindVertexArray();


    // draw calls

    /** Draw index buffer from current vertex array */
    void drawIndices(PrimitiveType primitiveType, GLType indexBufferElementType, int count);

    void bindIndexArray(IndexBuffer indices);
    void unbindIndexArray();

    /** Utility method that clears main framebuffer color to specified value */
    void clearColorBuffer(float[] clearColor);
    /** Utility method that clears main framebuffer depth with specified value */
    void clearDepthBuffer(float val);

    /** Enable depth test */
    void enableDepthTest();
    /** Disable depth test */
    void disableDepthTest();
}
