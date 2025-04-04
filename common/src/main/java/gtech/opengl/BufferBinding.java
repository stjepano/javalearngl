package gtech.opengl;

public interface BufferBinding extends AutoCloseable {
    void upload(float[] data);
    void upload(int[] data);

    BufferMapping map();

    @Override
    void close();
}
