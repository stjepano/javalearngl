package gtech.math;

import java.nio.FloatBuffer;
import java.util.Objects;

public final class AARect {
    public float x, y, width, height;
    
    public AARect() {
        this(0, 0, 0, 0);
    }
    
    public AARect(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public AARect(float[] array) {
        this(array, 0);
    }
    
    public AARect(float[] array, int offset) {
        this(array[offset], array[offset + 1], array[offset + 2], array[offset + 3]);
    }
    
    public float[] toArray() {
        return toArray(new float[4]);
    }
    
    public float[] toArray(float[] array) {
        return toArray(array, 0);
    }
    
    public float[] toArray(float[] array, int offset) {
        array[offset] = x;
        array[offset + 1] = y;
        array[offset + 2] = width;
        array[offset + 3] = height;
        return array;
    }
    
    public void putToBuffer(FloatBuffer buffer) {
        buffer.put(x);
        buffer.put(y);
        buffer.put(width);
        buffer.put(height);
    }
    
    public void putToBuffer(int offset, FloatBuffer buffer) {
        buffer.put(offset, x);
        buffer.put(offset + 1, y);
        buffer.put(offset + 2, width);
        buffer.put(offset + 3, height);
    }

    public void set(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void set(float[] array) {
        this.set(array, 0);
    }

    public void set(float[] array, int offset) {
        this.x = array[offset];
        this.y = array[offset + 1];
        this.width = array[offset + 2];
        this.height = array[offset + 3];
    }

    public boolean isEmpty() {
        return width <= 0 || height <= 0;
    }

    public boolean contains(float x, float y) {
        return x >= this.x && x < this.x + width && y >= this.y && y < this.y + height;
    }

    public boolean contains(AARect other) {
        return contains(other.x, other.y) && contains(other.x + other.width, other.y + other.height);
    }

    public AARect intersection(AARect out, AARect other) {
        out.x = Math.max(x, other.x);
        out.y = Math.max(y, other.y);
        out.width = Math.min(x + width, other.x + other.width) - out.x;
        out.height = Math.min(y + height, other.y + other.height) - out.y;
        return out;
    }

    @Override
    public String toString() {
        return "AARect{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AARect aaRect = (AARect) o;
        return Float.compare(x, aaRect.x) == 0 && Float.compare(y, aaRect.y) == 0 && Float.compare(width, aaRect.width) == 0 && Float.compare(height, aaRect.height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, width, height);
    }
}
