package gtech.opengl;

public interface VertexArrayBuilder {

    VertexArrayBuilder fromBuffer(VertexBuffer buffer);

    VertexArrayBuilder bindAttribute(int layoutLocation, GLType type, int count, int stride, int offset);

    VertexArray build();
}
