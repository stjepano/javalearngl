package gtech.opengl;

public interface Texture extends AutoCloseable {

    int getWidth();
    int getHeight();
    TextureFormat getFormat();

    @Override
    void close();


}
