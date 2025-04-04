package learngl;

import gtech.math.Mat4;
import gtech.math.Vec3;
import gtech.platform.Button;
import gtech.platform.Key;

public final class DemoCamera {
    private static final Vec3 FWD = Vec3.of(0, 0, -1);
    private static final Vec3 UP = Vec3.of(0, 1, 0);
    private static final Vec3 RIGHT = Vec3.of(1, 0, 0);

    private final Key moveForwardKey;
    private final Key moveLeftKey;
    private final Key moveRightKey;
    private final Key moveBackKey;
    private final Key moveUpKey;
    private final Key moveDownKey;
    private final Button activationButton;

    private final Vec3 position = new Vec3();
    private final Mat4 viewMatrix = Mat4.identity();

    private float movementSpeed = 10.0f;
    private boolean shouldRecalculateViewMatrix = false;

    public DemoCamera(InputMapping inputMapping) {
        this.moveForwardKey = inputMapping.moveForwardKey();
        this.moveLeftKey = inputMapping.moveLeftKey();
        this.moveRightKey = inputMapping.moveRightKey();
        this.moveBackKey = inputMapping.moveBackKey();
        this.moveUpKey = inputMapping.moveUpKey();
        this.moveDownKey = inputMapping.moveDownKey();
        this.activationButton = inputMapping.activationButton();
    }

    public void update(float deltaTime) {
        if (activationButton.isPressed()) {
            if (moveForwardKey.isPressed()) {
                position.addSelf(FWD.muls(deltaTime * movementSpeed));
                shouldRecalculateViewMatrix = true;
            }
            if (moveBackKey.isPressed()) {
                position.addSelf(FWD.muls(-deltaTime * movementSpeed));
                shouldRecalculateViewMatrix = true;
            }
            if (moveRightKey.isPressed()) {
                position.addSelf(RIGHT.muls(deltaTime * movementSpeed));
                shouldRecalculateViewMatrix = true;
            }
            if (moveLeftKey.isPressed()) {
                position.addSelf(RIGHT.muls(-deltaTime * movementSpeed));
                shouldRecalculateViewMatrix = true;
            }
            if (moveUpKey.isPressed()) {
                position.addSelf(UP.muls(deltaTime * movementSpeed));
                shouldRecalculateViewMatrix = true;
            }
            if (moveDownKey.isPressed()) {
                position.addSelf(UP.muls(-deltaTime * movementSpeed));
                shouldRecalculateViewMatrix = true;
            }
        }

        if (shouldRecalculateViewMatrix) {
            viewMatrix.setLookAt(position, position.add(FWD), UP);
        }
    }

    public void setPosition(Vec3 position) {
        this.position.set(position);
        this.shouldRecalculateViewMatrix = true;
    }

    public Vec3 getPosition() {
        return position;
    }

    public Mat4 getViewMatrix() {
        return viewMatrix;
    }

    public float getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public record InputMapping(Key moveForwardKey, Key moveLeftKey, Key moveRightKey, Key moveBackKey, Key moveUpKey,
                               Key moveDownKey, Button activationButton) {
    }
}
