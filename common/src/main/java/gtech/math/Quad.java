package gtech.math;

import java.nio.FloatBuffer;

public final class Quad {
    public final Vec2[] points = new Vec2[4];

    public Quad() {
        points[0] = new Vec2();
        points[1] = new Vec2();
        points[2] = new Vec2();
        points[3] = new Vec2();
    }

    public Quad(Vec2 p1, Vec2 p2, Vec2 p3, Vec2 p4) {
        points[0] = p1;
        points[1] = p2;
        points[2] = p3;
        points[3] = p4;
    }

    public Quad(AARect rect) {
        this(
                new Vec2(rect.x, rect.y),
                new Vec2(rect.x + rect.width, rect.y),
                new Vec2(rect.x + rect.width, rect.y + rect.height),
                new Vec2(rect.x, rect.y + rect.height)
        );
    }


    public static Quad ofCenterAndSize(float x, float y, float width, float height) {
        float left = x - width / 2;
        float right = x + width / 2;
        float top = y + height / 2;
        float bottom = y - height / 2;

        return new Quad(new Vec2(left, top), new Vec2(left, bottom), new Vec2(right, bottom), new Vec2(right, top));
    }

    public static Quad ofAARect(AARect rect) {
        return new Quad(rect);
    }

    public float[] toArray() {
        return toArray(new float[8]);
    }

    public float[] toArray(float[] array) {
        return toArray(array, 0);
    }

    public float[] toArray(float[] array, int offset) {
        points[0].toArray(array, offset);
        points[1].toArray(array, offset + 2);
        points[2].toArray(array, offset + 4);
        points[3].toArray(array, offset + 6);
        return array;
    }

    public void putToBuffer(FloatBuffer buffer) {
        points[0].putToBuffer(buffer);
        points[1].putToBuffer(buffer);
        points[2].putToBuffer(buffer);
        points[3].putToBuffer(buffer);
    }

    public void putToBuffer(int offset, FloatBuffer buffer) {
        points[0].putToBuffer(offset, buffer);
        points[1].putToBuffer(offset + 2, buffer);
        points[2].putToBuffer(offset + 4, buffer);
        points[3].putToBuffer(offset + 6, buffer);
    }

    public void rotate(float angleRadians) {
        for (Vec2 point : points) {
            point.rotateSelf(angleRadians);
        }
    }


}
