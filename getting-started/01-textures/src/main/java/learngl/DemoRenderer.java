package learngl;

import gtech.exception.GtechException;
import gtech.math.Quad;
import gtech.opengl.*;
import gtech.util.Resources;

public final class DemoRenderer implements AutoCloseable {
    private final GLMaster glMaster;

    private ShaderProgram program;
    private int uTexture1Location;
    private int uTexture2Location;

    private VertexArray vertexArray;
    private VertexBuffer positions;
    private VertexBuffer colorsAndTexCoords;
    private IndexBuffer indices;

    private TextureSampler textureSampler = TextureSampler.DEFAULT;

    public DemoRenderer(GLMaster glMaster) {
        this.glMaster = glMaster;
        this.init();
    }

    private void init() {

        final String vertexShaderSource = Resources.readText("/shaders/demo/vertex.glsl");
        final String fragmentShaderSource = Resources.readText("/shaders/demo/fragment.glsl");

        program = glMaster.createProgram(vertexShaderSource, fragmentShaderSource);
        uTexture1Location = program.getUniformLocationOrThrow("uTexture1", () -> new GtechException("No uTexture1 uniform"));
        uTexture2Location = program.getUniformLocationOrThrow("uTexture2", () -> new GtechException("No uTexture2 uniform"));

        positions = glMaster.createVertexBuffer(GLType.FLOAT.size() * 2 * 4, BufferUsage.DYNAMIC_DRAW);
        final float[] positionsData = new float[] {
                -0.5f,   0.5f,
                -0.5f,  -0.5f,
                 0.5f,  -0.5f,
                 0.5f,   0.5f
        };
        try (var binding = positions.bind()) {
            binding.upload(positionsData);
        }
        colorsAndTexCoords = glMaster.createVertexBuffer(GLType.FLOAT.size() * 5 * 4, BufferUsage.STATIC_DRAW);
        final float[] colorsAndTexCoordsData = new float[] {
                1.0f, 0.0f, 0.0f,  0.0f, 1.0f,
                0.0f, 1.0f, 0.0f,  0.0f, 0.0f,
                0.0f, 0.0f, 1.0f,  1.0f, 0.0f,
                1.0f, 1.0f, 1.0f,  1.0f, 1.0f
        };
        try (var binding = colorsAndTexCoords.bind()) {
            binding.upload(colorsAndTexCoordsData);
        }


        indices = glMaster.createIndexBuffer(GLType.UINT, 6, BufferUsage.STATIC_DRAW);
        final int[] indicesData = new int[] {
                0, 1, 2,
                2, 3, 0
        };
        try (var binding = indices.bind()) {
            binding.upload(indicesData);
        }

        vertexArray = glMaster.vertexArrayBuilder()
                .fromBuffer(positions)
                .bindAttribute(0, GLType.FLOAT, 2, GLType.FLOAT.size() * 2, 0)
                .fromBuffer(colorsAndTexCoords)
                .bindAttribute(1, GLType.FLOAT, 2, GLType.FLOAT.size() * 5, GLType.FLOAT.size() * 3)
                .bindAttribute(2, GLType.FLOAT, 3, GLType.FLOAT.size() * 5, 0)
                .build();

    }


    public void setTextureSampler(TextureSampler textureSampler) {
        this.textureSampler = textureSampler;
    }

    public TextureSampler getTextureSampler() {
        return textureSampler;
    }

    void draw(Quad quad, Texture texture, Texture faceTexture) {
        try (var binding = positions.bind()) {
            try (var bufferMapping = binding.map()) {
                quad.putToBuffer(0, bufferMapping.buffer().asFloatBuffer());
            }
        }

        glMaster.bindTexture(TextureUnit.UNIT0, texture, this.textureSampler);
        glMaster.bindTexture(TextureUnit.UNIT1, faceTexture, this.textureSampler);
        glMaster.bindVertexArray(this.vertexArray);
        glMaster.bindProgram(this.program);
        this.program.setUniform(uTexture1Location, TextureUnit.UNIT0);
        this.program.setUniform(uTexture2Location, TextureUnit.UNIT1);

        glMaster.bindIndexArray(this.indices);
        glMaster.drawIndices(PrimitiveType.TRIANGLES, GLType.UINT, 6);
        glMaster.unbindIndexArray();


        glMaster.unbindVertexArray();
        glMaster.unbindTexture(TextureUnit.UNIT0);
        glMaster.unbindProgram();
    }

    @Override
    public void close() {
        this.positions.close();
        this.colorsAndTexCoords.close();
        this.indices.close();
        this.vertexArray.close();
        this.program.close();
    }
}
