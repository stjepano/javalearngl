package learngl;

import gtech.exception.GLUniformNotFoundException;
import gtech.math.Mat4;
import gtech.opengl.*;
import gtech.util.Resources;

public final class DemoMeshRenderer implements AutoCloseable {

    public final GLMaster glMaster;
    public final DemoMesh mesh;

    private ShaderProgram program;
    private int uProjectionMatrixLocation;
    private int uViewMatrixLocation;
    private int uModelMatrixLocation;
    private int uTexture1Location;
    private int uTexture2Location;

    private VertexArray vertexArray;
    private VertexBuffer vertices;
    private IndexBuffer indices;

    private Mat4 projectionMatrix = Mat4.identity();

    private TextureSampler textureSampler = TextureSampler.DEFAULT;

    public DemoMeshRenderer(GLMaster glMaster, DemoMesh mesh) {
        this.glMaster = glMaster;
        this.mesh = mesh;

        this.init();
    }

    private void init() {
        final String vertexShaderSource = Resources.readText("/shaders/demo/mesh-vertex.glsl");
        final String fragmentShaderSource = Resources.readText("/shaders/demo/mesh-fragment.glsl");

        program = glMaster.createProgram(vertexShaderSource, fragmentShaderSource);
        uProjectionMatrixLocation = program.getUniformLocationOrThrow("uProjectionMatrix", () -> new GLUniformNotFoundException("uProjectionMatrix"));
        uViewMatrixLocation = program.getUniformLocationOrThrow("uViewMatrix", () -> new GLUniformNotFoundException("uViewMatrix"));
        uModelMatrixLocation = program.getUniformLocationOrThrow("uModelMatrix", () -> new GLUniformNotFoundException("uModelMatrix"));
        uTexture1Location = program.getUniformLocationOrThrow("uTexture1", () -> new GLUniformNotFoundException("uTexture1"));
        uTexture2Location = program.getUniformLocationOrThrow("uTexture2", () -> new GLUniformNotFoundException("uTexture2"));

        float[] meshVertices = this.mesh.getVertices();
        int[] meshIndices = this.mesh.getIndices();

        vertices = glMaster.createVertexBuffer(meshVertices.length * GLType.FLOAT.size() * 5, BufferUsage.STATIC_DRAW);
        try (var binding = vertices.bind()) {
            binding.upload(meshVertices);
        }

        indices = glMaster.createIndexBuffer(GLType.UINT, meshIndices.length, BufferUsage.STATIC_DRAW);
        try (var binding = indices.bind()) {
            binding.upload(meshIndices);
        }

        vertexArray = glMaster.vertexArrayBuilder()
                .fromBuffer(vertices)
                .bindAttribute(0, GLType.FLOAT, 3, GLType.FLOAT.size() * 5, 0)
                .bindAttribute(1, GLType.FLOAT, 2, GLType.FLOAT.size() * 5, GLType.FLOAT.size() * 3)
                .build();
    }

    public void setProjectionMatrix(Mat4 projectionMatrix) {
        this.projectionMatrix = projectionMatrix;
    }

    public Mat4 getProjectionMatrix() {
        return projectionMatrix;
    }

    public void setTextureSampler(TextureSampler textureSampler) {
        this.textureSampler = textureSampler;
    }

    public TextureSampler getTextureSampler() {
        return textureSampler;
    }

    void draw(Mat4 viewMatrix, Mat4 modelMatrix, Texture texture, Texture faceTexture) {
        glMaster.enableDepthTest();

        glMaster.bindTexture(TextureUnit.UNIT0, texture, this.textureSampler);
        glMaster.bindTexture(TextureUnit.UNIT1, faceTexture, this.textureSampler);
        glMaster.bindVertexArray(this.vertexArray);
        glMaster.bindProgram(this.program);
        this.program.setUniform(uProjectionMatrixLocation, this.projectionMatrix);
        this.program.setUniform(uViewMatrixLocation, viewMatrix);
        this.program.setUniform(uModelMatrixLocation, modelMatrix);
        this.program.setUniform(uTexture1Location, TextureUnit.UNIT0);
        this.program.setUniform(uTexture2Location, TextureUnit.UNIT1);

        glMaster.bindIndexArray(this.indices);
        glMaster.drawIndices(PrimitiveType.TRIANGLES, GLType.UINT, mesh.getIndices().length);
        glMaster.unbindIndexArray();

        glMaster.unbindVertexArray();
        glMaster.unbindTexture(TextureUnit.UNIT0);
        glMaster.unbindTexture(TextureUnit.UNIT1);
        glMaster.unbindProgram();

        glMaster.disableDepthTest();
    }

    @Override
    public void close() {
        this.vertices.close();
        this.indices.close();
        this.vertexArray.close();
        this.program.close();
    }
}
