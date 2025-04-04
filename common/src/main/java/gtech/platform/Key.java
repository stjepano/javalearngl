package gtech.platform;

public interface Key {

    /**
     * Check if the key is pressed down.
     * @return true if key is pressed down, false if not.
     */
    boolean isPressed();

    /**
     * Check if the key was just pressed (transitioned from released to pressed state).
     * @return true if key was just pressed.
     */
    boolean wasJustPressed();

    /**
     * Check if key was just released (transition from pressed to released state).
     * @return true if key was just released.
     */
    boolean wasJustReleased();
}
