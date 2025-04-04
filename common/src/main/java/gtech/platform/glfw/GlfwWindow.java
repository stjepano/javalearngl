package gtech.platform.glfw;

import gtech.exception.GtechException;
import gtech.math.IntVec2;
import gtech.platform.Keyboard;
import gtech.platform.Mouse;
import gtech.platform.WindowProperties;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.system.Platform;

public class GlfwWindow implements gtech.platform.Window {
    private long handle = 0L;
    private boolean shouldVsync = false;
    private final GlfwKeyboard keyboard;
    private final GlfwMouse mouse;

    public GlfwWindow(WindowProperties properties) {
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, properties.isVisible() ? GLFW_TRUE : GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, properties.isResizable() ? GLFW_TRUE : GLFW_FALSE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, properties.getGlVersion().x);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, properties.getGlVersion().y);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        if (Platform.get() == Platform.MACOSX) {
            glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
        }
        this.shouldVsync = properties.isVsync();

        handle = glfwCreateWindow(properties.getSize().x, properties.getSize().y,
                properties.getTitle(), 0, 0);
        if (handle == 0L) {
            throw new GtechException("Failed to create the GLFW window!");
        }

        keyboard = new GlfwKeyboard();
        mouse = new GlfwMouse();

        glfwSetKeyCallback(handle, (window, key, scancode, action, mods) -> {
            if (window != handle) return;

            if (action == GLFW_PRESS) {
                keyboard.onKeyPressed(key);
            } else if (action == GLFW_RELEASE) {
                keyboard.onKeyReleased(key);
            }
        });

        glfwSetCursorPosCallback(handle, (window, xpos, ypos) -> {
            if (window != handle) return;

            mouse.onMousePosition(xpos, ypos);
        });

        glfwSetMouseButtonCallback(handle, (window, button, action, mods) -> {
            if (window != handle) return;

            if (action == GLFW_PRESS) {
                mouse.onButtonPressed(button);
            } else if (action == GLFW_RELEASE) {
                mouse.onButtonReleased(button);
            }
        });

        glfwSetScrollCallback(handle, (window, xoffset, yoffset) -> {
            if (window != handle) return;

            mouse.onScroll(yoffset);
        });
    }

    @Override
    public void show() {
        glfwShowWindow(handle);
    }

    @Override
    public void hide() {
        glfwHideWindow(handle);
    }

    @Override
    public void getFramebufferSize(IntVec2 outSize) {
        int[] pWidth = new int[1];
        int[] pHeight = new int[1];
        glfwGetWindowSize(handle, pWidth, pHeight);
        outSize.x = pWidth[0];
        outSize.y = pHeight[0];
    }

    @Override
    public void makeContextCurrent() {
        glfwMakeContextCurrent(handle);
        if (shouldVsync) {
            glfwSwapInterval(1);
        } else {
            glfwSwapInterval(0);
        }
    }

    @Override
    public boolean isCloseRequested() {
        return glfwWindowShouldClose(handle);
    }

    @Override
    public void requestClose() {
        glfwSetWindowShouldClose(handle, true);
    }

    @Override
    public void swapBuffers() {
        glfwSwapBuffers(handle);
    }

    @Override
    public void close() {
        if (handle != 0L) {
            glfwDestroyWindow(handle);
        }
        handle = 0L;
    }

    @Override
    public Keyboard getKeyboard() {
        return keyboard;
    }

    @Override
    public Mouse getMouse() {
        return mouse;
    }

    public void onBeforePollEvents() {
        keyboard.updateStates();
        mouse.updateStates();
    }

    public Long getHandle() {
        return handle;
    }
}
