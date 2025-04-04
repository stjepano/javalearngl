package gtech.platform;

import gtech.math.Vec2;

public interface Mouse {

    /** Get the position of the mouse */
    void getPosition(Vec2 position);

    /** Get the position of the mouse (creates new Vec2) */
    default Vec2 getPosition() {
        Vec2 position = new Vec2();
        getPosition(position);
        return position;
    }

    /** Get the mouse button */
    Button getButton(ButtonCode buttonCode);

    /** Get the current scroll offset (if any). */
    float getScrollDelta();

}
