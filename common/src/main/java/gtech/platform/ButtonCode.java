package gtech.platform;

import static org.lwjgl.glfw.GLFW.*;

public enum ButtonCode {

    UNKNOWN(-1),
    LEFT(GLFW_MOUSE_BUTTON_LEFT),
    MIDDLE(GLFW_MOUSE_BUTTON_MIDDLE),
    RIGHT(GLFW_MOUSE_BUTTON_RIGHT),
    LAST(GLFW_MOUSE_BUTTON_LAST);

    private final int glfwButton;

    ButtonCode(int glfwButton) {
        this.glfwButton = glfwButton;
    }

    public int getGlfwButton() {
        return glfwButton;
    }

    public static ButtonCode fromGlfwButton(int glfwButton) {
        return switch (glfwButton) {
            case GLFW_MOUSE_BUTTON_LEFT -> LEFT;
            case GLFW_MOUSE_BUTTON_MIDDLE -> MIDDLE;
            case GLFW_MOUSE_BUTTON_RIGHT -> RIGHT;
            default -> UNKNOWN;
        };
    }
}
