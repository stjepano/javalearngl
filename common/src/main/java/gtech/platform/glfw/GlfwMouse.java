package gtech.platform.glfw;

import gtech.math.Vec2;
import gtech.platform.Button;
import gtech.platform.ButtonCode;
import gtech.platform.Mouse;
import org.lwjgl.glfw.GLFW;

public final class GlfwMouse implements Mouse {
    private static final float CLICK_TOLERANCE_DISTANCE = 2.0f;
    private static final long DOUBLE_CLICK_LAG = 250;

    private static final int PRESSED_MASK = 1;
    private static final int TRANSITION_MASK = 2;
    private static final int CLICKED_MASK = 4;
    private static final int DOUBLE_CLICKED_MASK = 8;
    private static final int TRIPLE_CLICKED_MASK = 16;

    private final Vec2 position = new Vec2();
    private float scrollDelta = 0.0f;
    private final ButtonState[] buttonStates = new ButtonState[GLFW.GLFW_MOUSE_BUTTON_LAST];
    private final ButtonImpl[] buttons;

    private static class ButtonState {
        enum Action {
            NONE, PRESSED, RELEASED, CLICKED
        };
        public short flags = 0;
        public Action lastAction = Action.NONE;
        public long lastActionTime = 0;
        public Vec2 lastActionPosition = new Vec2();
        public int clickCount = 0;

        private boolean nearLastActionPosition(Vec2 position) {
            return position.sub(lastActionPosition).length() < CLICK_TOLERANCE_DISTANCE;
        }

        public void onPressed(Vec2 position) {
            flags |= PRESSED_MASK;
            flags |= TRANSITION_MASK;

            long now = System.currentTimeMillis();
            if (this.lastAction == Action.CLICKED && (!nearLastActionPosition(position) || now - lastActionTime > DOUBLE_CLICK_LAG)) {
                // can not be double or triple click, reset click count
                this.clickCount = 0;
            }


            this.lastAction = Action.PRESSED;
            this.lastActionTime = System.currentTimeMillis();
            this.lastActionPosition.set(position);
        }

        public void onReleased(Vec2 position) {
            flags |= TRANSITION_MASK;
            flags &= ~PRESSED_MASK;

            if (this.lastAction == Action.PRESSED) {
                if (nearLastActionPosition(position)) {
                    clickCount++;
                    if (clickCount == 1) {
                        flags |= CLICKED_MASK;
                    } else if (clickCount == 2) {
                        flags |= DOUBLE_CLICKED_MASK;
                    } else if (clickCount == 3) {
                        flags |= TRIPLE_CLICKED_MASK;
                        clickCount = 0;
                    }
                    this.lastAction = Action.CLICKED;
                    this.lastActionTime = System.currentTimeMillis();
                    this.lastActionPosition.set(position);
                    return;
                }
            }

            this.lastAction = Action.RELEASED;
            this.lastActionTime = System.currentTimeMillis();
            this.lastActionPosition.set(position);
        }

        public void reset() {
            flags &= ~TRANSITION_MASK;
            flags &= ~CLICKED_MASK;
            flags &= ~DOUBLE_CLICKED_MASK;
            flags &= ~TRIPLE_CLICKED_MASK;

            long now = System.currentTimeMillis();
            if (lastAction == Action.CLICKED && now - lastActionTime > DOUBLE_CLICK_LAG) {
                clickCount = 0;
            }
        }
    }


    private final class ButtonImpl implements Button {

        private final int glfwButtonCode;

        private ButtonImpl(int glfwButtonCode) {
            this.glfwButtonCode = glfwButtonCode;
        }

        @Override
        public boolean isPressed() {
            return (buttonStates[glfwButtonCode].flags & PRESSED_MASK) != 0;
        }

        @Override
        public boolean wasJustPressed() {
            return isPressed() && (buttonStates[glfwButtonCode].flags & TRANSITION_MASK) != 0;
        }

        @Override
        public boolean wasJustReleased() {
            return !isPressed() && (buttonStates[glfwButtonCode].flags & TRANSITION_MASK) != 0;
        }

        @Override
        public boolean wasClicked() {
            return (buttonStates[glfwButtonCode].flags & CLICKED_MASK) != 0;
        }

        @Override
        public boolean wasDoubleClicked() {
            return (buttonStates[glfwButtonCode].flags & DOUBLE_CLICKED_MASK) != 0;
        }

        @Override
        public boolean wasTripleClicked() {
            return (buttonStates[glfwButtonCode].flags & TRIPLE_CLICKED_MASK) != 0;
        }
    }

    public GlfwMouse() {
        buttons = new ButtonImpl[ButtonCode.LAST.ordinal()];
        for (int i = 0; i < buttonStates.length; i++) {
            buttonStates[i] = new ButtonState();
        }
        for (int i = 0; i < buttons.length; i++) {
            var buttonCodes = ButtonCode.values();
            buttons[i] = new ButtonImpl(buttonCodes[i].getGlfwButton());
        }
    }

    @Override
    public void getPosition(Vec2 position) {
        position.set(this.position);
    }

    @Override
    public Button getButton(ButtonCode buttonCode) {
        return buttons[buttonCode.ordinal()];
    }

    @Override
    public float getScrollDelta() {
        return scrollDelta;
    }

    public void onMousePosition(double x, double y) {
        position.set((float)x, (float)y);
    }

    public void onButtonPressed(int glfwButtonCode) {
        ButtonState buttonState = buttonStates[glfwButtonCode];
        buttonState.onPressed(this.position);
    }

    public void onButtonReleased(int glfwButtonCode) {
        ButtonState buttonState = buttonStates[glfwButtonCode];
        buttonState.onReleased(this.position);
    }


    public void onScroll(double offset) {
        scrollDelta += (float)offset;
    }

    public void updateStates() {
        for (ButtonState buttonState : buttonStates) {
            buttonState.reset();
        }
        scrollDelta = 0.0f;
    }
}
