package gtech.opengl.opengl3;

import gtech.opengl.*;

import static org.lwjgl.opengl.GL11C.*;

class GL3Texture implements Texture {
    private final GLMaster glMaster;
    private int textureId;
    private int width;
    private int height;
    private TextureFormat format;

    private TextureSampler appliedSampler = null;

    public GL3Texture(GLMaster glMaster, int textureId, int width, int height, TextureFormat format) {
        this.glMaster = glMaster;
        this.textureId = textureId;
        this.width = width;
        this.height = height;
        this.format = format;
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
    public TextureFormat getFormat() {
        return format;
    }

    @Override
    public void close() {
        if (textureId != 0) {
            glDeleteTextures(textureId);
            textureId = 0;
        }
    }

    int getId() {
        return this.textureId;
    }

    void applySampler(TextureSampler sampler) {
        if (sampler.equals(appliedSampler)) {
            return;
        }

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, sampler.getWrapS().getGlEnum());
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, sampler.getWrapT().getGlEnum());

        if (sampler.getMipmapFilter() != null) {
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, combineMipmapMin(sampler.getMinFilter(), sampler.getMipmapFilter()));
        } else {
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, sampler.getMinFilter().getGlEnum());
        }

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, sampler.getMagFilter().getGlEnum());

        if (sampler.getBorderColor() != null) {
            float[] colorArray = sampler.getBorderColor().toArray();
            glTexParameterfv(GL_TEXTURE_2D, GL_TEXTURE_BORDER_COLOR, colorArray);
        }

        appliedSampler = sampler;
    }

    private int combineMipmapMin(TextureFilter minFilter, TextureFilter mipmapFilter) {
        if (minFilter == TextureFilter.NEAREST) {
            if (mipmapFilter == TextureFilter.NEAREST) {
                return GL_NEAREST_MIPMAP_NEAREST;
            } else if (mipmapFilter == TextureFilter.LINEAR) {
                return GL_NEAREST_MIPMAP_LINEAR;
            }

        } else if (minFilter == TextureFilter.LINEAR) {
            if (mipmapFilter == TextureFilter.NEAREST) {
                return GL_LINEAR_MIPMAP_NEAREST;
            } else if (mipmapFilter == TextureFilter.LINEAR) {
                return GL_LINEAR_MIPMAP_LINEAR;
            }
        }

        throw new IllegalArgumentException("Invalid min/mipmap filter combination");
    }
}
