package gtech.platform;

import gtech.math.IntVec2;

public class WindowProperties {
    private final String title;
    private final IntVec2 size;
    private final boolean resizable;
    private final boolean visible;
    private final boolean vsync;
    private final IntVec2 glVersion;

    public WindowProperties(String title, IntVec2 size, boolean resizable, boolean visible, boolean vsync, IntVec2 glVersion) {
        this.title = title;
        this.size = size;
        this.resizable = resizable;
        this.visible = visible;
        this.vsync = vsync;
        this.glVersion = glVersion;
    }

    public String getTitle() {
        return title;
    }

    public IntVec2 getSize() {
        return size;
    }

    public boolean isResizable() {
        return resizable;
    }

    public boolean isVisible() {
        return visible;
    }

    public boolean isVsync() {
        return vsync;
    }

    public IntVec2 getGlVersion() {
        return glVersion;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String title = "GTech";
        private IntVec2 size = new IntVec2(1280, 720);
        private boolean resizable = true;
        private boolean visible = true;
        private boolean vsync = true;
        private IntVec2 glVersion = new IntVec2(3, 3);

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder size(int width, int height) {
            this.size.set(width, height);
            return this;
        }

        public Builder resizable(boolean resizable) {
            this.resizable = resizable;
            return this;
        }

        public Builder visible(boolean visible) {
            this.visible = visible;
            return this;
        }

        public Builder vsync(boolean vsync) {
            this.vsync = vsync;
            return this;
        }

        public Builder glVersion(int major, int minor) {
            this.glVersion.set(major, minor);
            return this;
        }

        public WindowProperties build() {
            return new WindowProperties(title, size, resizable, visible, vsync, glVersion);
        }
    }
    
}
