package gtech.opengl;

import gtech.math.Color4;

import java.util.Objects;

public final class TextureSampler {
    public static final TextureSampler DEFAULT = new TextureSampler(TextureWrap.REPEAT, TextureWrap.REPEAT, TextureFilter.NEAREST, TextureFilter.NEAREST, TextureFilter.NEAREST, Color4.black());

    private final TextureWrap wrapS;
    private final TextureWrap wrapT;
    private final TextureFilter minFilter;
    private final TextureFilter magFilter;
    private final TextureFilter mipmapFilter;
    private final Color4 borderColor;

    public TextureSampler(TextureWrap wrapS, TextureWrap wrapT, TextureFilter minFilter, TextureFilter magFilter, TextureFilter mipmapFilter, Color4 borderColor) {
        this.wrapS = wrapS;
        this.wrapT = wrapT;
        this.minFilter = minFilter;
        this.magFilter = magFilter;
        this.mipmapFilter = mipmapFilter;
        this.borderColor = borderColor;
    }

    public TextureWrap getWrapS() {
        return wrapS;
    }

    public TextureWrap getWrapT() {
        return wrapT;
    }

    public TextureFilter getMinFilter() {
        return minFilter;
    }

    public TextureFilter getMagFilter() {
        return magFilter;
    }

    public TextureFilter getMipmapFilter() {
        return mipmapFilter;
    }

    public Color4 getBorderColor() {
        return borderColor;
    }
    
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "TextureSampler{" +
                "wrapS=" + wrapS +
                ", wrapT=" + wrapT +
                ", minFilter=" + minFilter +
                ", magFilter=" + magFilter +
                ", mipmapFilter=" + mipmapFilter +
                ", borderColor=" + borderColor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TextureSampler that = (TextureSampler) o;
        return wrapS == that.wrapS && wrapT == that.wrapT && minFilter == that.minFilter && magFilter == that.magFilter && mipmapFilter == that.mipmapFilter && Objects.equals(borderColor, that.borderColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wrapS, wrapT, minFilter, magFilter, mipmapFilter, borderColor);
    }

    public static class Builder {
        private TextureWrap wrapS = TextureWrap.REPEAT;
        private TextureWrap wrapT = TextureWrap.REPEAT;
        private TextureFilter minFilter = TextureFilter.LINEAR;
        private TextureFilter magFilter = TextureFilter.LINEAR;
        private TextureFilter mipmapFilter = null;
        private Color4 borderColor = Color4.black();

        public Builder wrap(TextureWrap wrapS, TextureWrap wrapT) {
            this.wrapS = wrapS;
            this.wrapT = wrapT;
            return this;
        }

        public Builder filter(TextureFilter minFilter, TextureFilter magFilter) {
            this.minFilter = minFilter;
            this.magFilter = magFilter;
            return this;
        }

        public Builder mipmapFilter(TextureFilter mipmapFilter) {
            this.mipmapFilter = mipmapFilter;
            return this;
        }

        public Builder borderColor(Color4 borderColor) {
            this.borderColor = borderColor;
            return this;
        }

        public TextureSampler build() {
            return new TextureSampler(wrapS, wrapT, minFilter, magFilter, mipmapFilter, borderColor);
        }
    }
}
