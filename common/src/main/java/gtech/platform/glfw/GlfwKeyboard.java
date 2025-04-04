package gtech.platform.glfw;

import gtech.platform.Key;
import gtech.platform.KeyCode;
import gtech.platform.Keyboard;
import org.lwjgl.glfw.GLFW;

public final class GlfwKeyboard implements Keyboard {
    private static final int PRESSED_MASK = 1;
    private static final int TRANSITION_MASK = 2;

    private final short[] keyStates = new short[GLFW.GLFW_KEY_LAST];
    private final KeyImpl[] keys;

    private final class KeyImpl implements Key {
        private final int glfwKeyCode;

        private KeyImpl(int glfwKeyCode) {
            this.glfwKeyCode = glfwKeyCode;
        }

        @Override
        public boolean isPressed() {
            return (keyStates[glfwKeyCode] & PRESSED_MASK) != 0;
        }

        @Override
        public boolean wasJustPressed() {
            return isPressed() && (keyStates[glfwKeyCode] & TRANSITION_MASK) != 0;
        }

        @Override
        public boolean wasJustReleased() {
            return !isPressed() && (keyStates[glfwKeyCode] & TRANSITION_MASK) != 0;
        }
    }

    public GlfwKeyboard() {
        keys = new KeyImpl[KeyCode.LAST.ordinal()];
        for (int i = 0; i < keys.length; i++) {
            var keyCodes = KeyCode.values();
            keys[i] = new KeyImpl(keyCodes[i].getGlfwKey());
        }
    }

    @Override
    public Key getKey(KeyCode keyCode) {
        return this.keys[keyCode.ordinal()];
    }

    public void onKeyPressed(int glfwKeyCode) {
        keyStates[glfwKeyCode] |= (PRESSED_MASK | TRANSITION_MASK);
    }

    public void onKeyReleased(int glfwKeyCode) {
        keyStates[glfwKeyCode] |= TRANSITION_MASK;
        keyStates[glfwKeyCode] &= ~PRESSED_MASK;
    }

    public void updateStates() {
        for (int i = 0; i < keyStates.length; i++) {
            keyStates[i] &= ~TRANSITION_MASK;
        }
    }
}
