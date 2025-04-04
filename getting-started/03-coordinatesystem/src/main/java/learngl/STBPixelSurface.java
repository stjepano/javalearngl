package learngl;

import gtech.data.PixelSurface;
import gtech.exception.GtechException;
import org.lwjgl.stb.STBImage;

import java.io.File;
import java.nio.ByteBuffer;

public class STBPixelSurface implements PixelSurface {
    private int width;
    private int height;
    private Format format;
    private ByteBuffer pixels;

    private STBPixelSurface(int width, int height, Format format, ByteBuffer pixels) {
        this.width = width;
        this.height = height;
        this.format = format;
        this.pixels = pixels;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public Format getFormat() {
        return format;
    }

    @Override
    public ByteBuffer getPixels() {
        return pixels;
    }

    @Override
    public void close() {
        if (pixels != null) {
            STBImage.stbi_image_free(pixels);
            pixels = null;
        }
    }

    public static STBPixelSurface load(File file, boolean flipVertically) {
        if (!file.exists() || !file.canRead()) {
            throw new GtechException("File does not exist or cannot be read: " + file.getAbsolutePath());
        }

        String absolutePath = file.getAbsolutePath();
        int[] width = new int[1];
        int[] height = new int[1];
        int[] components = new int[1];
        if (flipVertically) {
            STBImage.stbi_set_flip_vertically_on_load(true);
        } else {
            STBImage.stbi_set_flip_vertically_on_load(false);
        }
        ByteBuffer pixels = STBImage.stbi_load(absolutePath, width, height, components, 4);
        if (pixels == null) {
            throw new GtechException("Failed to load image: " + STBImage.stbi_failure_reason());
        }

        Format pixelSurfaceFormat = switch (components[0]) {
            case 1 -> Format.RED8;
            case 3 -> Format.RGB8;
            case 4 -> Format.RGBA8;
            default -> throw new GtechException("Unsupported number of components: " + components[0]);
        };

        return new STBPixelSurface(width[0], height[0], pixelSurfaceFormat, pixels);
    }
}
