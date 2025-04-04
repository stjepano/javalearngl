package gtech.opengl;

import java.nio.ByteBuffer;

public interface BufferMapping extends AutoCloseable {

    ByteBuffer buffer();

    @Override
    void close();
}
