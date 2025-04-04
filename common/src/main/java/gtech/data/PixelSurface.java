package gtech.data;

import java.nio.ByteBuffer;

public interface PixelSurface extends AutoCloseable {

    enum Format {
        RED8,
        RGB8,
        RGBA8;
    }

    int getWidth();
    int getHeight();
    Format getFormat();

    ByteBuffer getPixels();

    @Override
    void close();
}
