package gtech.platform;

public interface Button {

    /** Return true if mouse button is down */
    boolean isPressed();

    /** Return true if mouse button has transitioned to pressed state */
    boolean wasJustPressed();

    /** Return true if mouse button has transitioned to released state */
    boolean wasJustReleased();

    /** Return true if mouse was just clicked (pressed and released) on same position. */
    boolean wasClicked();

    /** Return true if mouse button was double clicked */
    boolean wasDoubleClicked();

    /** Return true if mouse button was triple clicked */
    boolean wasTripleClicked();
}
