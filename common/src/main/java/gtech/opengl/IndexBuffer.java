package gtech.opengl;

public interface IndexBuffer extends AutoCloseable {

    BufferBinding bind();

    @Override
    void close();
}
