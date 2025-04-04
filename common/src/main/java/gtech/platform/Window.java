package gtech.platform;

import gtech.math.IntVec2;

public interface Window extends AutoCloseable {

    /**
     * Show the window (if it is hidden).
     */
    void show();

    /**
     * Hide the window.
     */
    void hide();

    /**
     * Get framebuffer size.
     * @param outSize where to store the size
     */
    void getFramebufferSize(IntVec2 outSize);

    /**
     * Make the OpenGL context current (all opengl commands will target this window now).
     */
    void makeContextCurrent();


    /**
     * @return true if close requested
     */
    boolean isCloseRequested();

    /**
     * Next call to isCloseRequested will return true.
     */
    void requestClose();

    /**
     * Swap the buffers (present rendering).
     */
    void swapBuffers();

    /**
     * Close the window.
     */
    @Override
    void close();

    /**
     * Get the keyboard (which monitors key states for this window).
     * @return the {@link Keyboard}
     */
    Keyboard getKeyboard();

    /** Get the mouse (for the window) */
    Mouse getMouse();

}
