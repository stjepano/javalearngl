package gtech.math;

import java.nio.FloatBuffer;
import java.util.Objects;

public final class AACube {
    public float x, y, z, width, height, depth;
    
    public AACube() {
        this(0, 0, 0, 0, 0, 0);
    }
    
    /** Constructs with one corner and size */
    public AACube(float x, float y, float z, float width, float height, float depth) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.height = height;
        this.depth = depth;
    }
    
    public AACube(float[] array) {
        this(array, 0);
    }
    
    public AACube(float[] array, int offset) {
        this(array[offset], array[offset + 1], array[offset + 2], 
             array[offset + 3], array[offset + 4], array[offset + 5]);
    }
    
    /** Constructs from center and size */
    public static AACube ofCenterAndSize(float cx, float cy, float cz, float width, float height, float depth) {
        float halfWidth = width / 2f;
        float halfHeight = height / 2f;
        float halfDepth = depth / 2f;
        
        return new AACube(cx - halfWidth, cy - halfHeight, cz - halfDepth, width, height, depth);
    }
    
    public float[] toArray() {
        return toArray(new float[6]);
    }
    
    public float[] toArray(float[] array) {
        return toArray(array, 0);
    }
    
    public float[] toArray(float[] array, int offset) {
        array[offset] = x;
        array[offset + 1] = y;
        array[offset + 2] = z;
        array[offset + 3] = width;
        array[offset + 4] = height;
        array[offset + 5] = depth;
        return array;
    }
    
    public void putToBuffer(FloatBuffer buffer) {
        buffer.put(x);
        buffer.put(y);
        buffer.put(z);
        buffer.put(width);
        buffer.put(height);
        buffer.put(depth);
    }
    
    public void putToBuffer(int offset, FloatBuffer buffer) {
        buffer.put(offset, x);
        buffer.put(offset + 1, y);
        buffer.put(offset + 2, z);
        buffer.put(offset + 3, width);
        buffer.put(offset + 4, height);
        buffer.put(offset + 5, depth);
    }

    public void set(float x, float y, float z, float width, float height, float depth) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public void set(float[] array) {
        set(array, 0);
    }

    public void set(float[] array, int offset) {
        set(array[offset], array[offset + 1], array[offset + 2],
                array[offset + 3], array[offset + 4], array[offset + 5]);
    }

    public boolean contains(float x, float y, float z) {
        return x >= this.x && x < this.x + width &&
                y >= this.y && y < this.y + height &&
                z >= this.z && z < this.z + depth;
    }

    public boolean contains(AACube other) {
        return contains(other.x, other.y, other.z) &&
                contains(other.x + other.width, other.y + other.height, other.z + other.depth);
    }

    public AACube intersection(AACube other) {
        return intersection(new AACube(), other);
    }

    public AACube intersection(AACube out, AACube other) {
        out.x = Math.max(x, other.x);
        out.y = Math.max(y, other.y);
        out.z = Math.max(z, other.z);
        out.width = Math.min(x + width, other.x + other.width) - out.x;
        out.height = Math.min(y + height, other.y + other.height) - out.y;
        out.depth = Math.min(z + depth, other.z + other.depth) - out.z;
        return out;
    }

    @Override
    public String toString() {
        return "AACube{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", width=" + width +
                ", height=" + height +
                ", depth=" + depth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AACube aaCube = (AACube) o;
        return Float.compare(x, aaCube.x) == 0 && Float.compare(y, aaCube.y) == 0 && Float.compare(z, aaCube.z) == 0 && Float.compare(width, aaCube.width) == 0 && Float.compare(height, aaCube.height) == 0 && Float.compare(depth, aaCube.depth) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, width, height, depth);
    }
}
