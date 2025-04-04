package gtech.opengl;

public interface VertexBuffer extends AutoCloseable {

    BufferBinding bind();

    @Override
    void close();
}
