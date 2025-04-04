package gtech.platform;

import gtech.exception.GtechException;
import gtech.platform.glfw.GlfwWindow;

import java.util.WeakHashMap;

import static org.lwjgl.glfw.GLFW.*;

public final class LwjglPlatform implements AutoCloseable {
    private final WeakHashMap<GlfwWindow, Long> windows = new WeakHashMap<>();

    public LwjglPlatform() {
        if (!glfwInit()) {
            throw new GtechException("Failed to initialize GLFW!");
        }
    }

    @Override
    public void close() throws Exception {
        glfwTerminate();
    }

    public void pollEvents() {
        for (GlfwWindow window : windows.keySet()) {
            window.onBeforePollEvents();
        }
        glfwPollEvents();
    }

    public Window createWindow(WindowProperties properties) {
        GlfwWindow window = new GlfwWindow(properties);
        windows.put(window, window.getHandle());
        return window;
    }


}
