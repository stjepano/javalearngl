package gtech.math;

import java.nio.FloatBuffer;
import java.util.Objects;

public final class Vec4 {
    public float x, y, z, w;

    public Vec4() {
        this(0, 0, 0, 0);
    }

    public Vec4(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
    
    public Vec4(Vec4 other) {
        this(other.x, other.y, other.z, other.w);
    }
    
    public Vec4(Vec3 v, float w) {
        this(v.x, v.y, v.z, w);
    }
    
    public Vec4(Vec2 v, float z, float w) {
        this(v.x, v.y, z, w);
    }
    
    public Vec4(float[] array) {
        this(array, 0);
    }
    
    public Vec4(float[] array, int offset) {
        this.x = array[offset];
        this.y = array[offset + 1];
        this.z = array[offset + 2];
        this.w = array[offset + 3];
    }
    
    public void set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
    
    public void set(Vec4 other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
        this.w = other.w;
    }
    
    public void set(Vec3 v, float w) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
        this.w = w;
    }
    
    public void set(Vec2 v, float z, float w) {
        this.x = v.x;
        this.y = v.y;
        this.z = z;
        this.w = w;
    }
    
    public void set(float[] array) {
        set(array, 0);
    }
    
    public void set(float[] array, int offset) {
        this.x = array[offset];
        this.y = array[offset + 1];
        this.z = array[offset + 2];
        this.w = array[offset + 3];
    }
    
    public float[] toArray() {
        return toArray(new float[4]);
    }
    
    public float[] toArray(float[] out) {
        return toArray(out, 0);
    }
    
    public float[] toArray(float[] out, int offset) {
        out[offset] = x;
        out[offset + 1] = y;
        out[offset + 2] = z;
        out[offset + 3] = w;
        return out;
    }
    
    public void putToBuffer(FloatBuffer buffer) {
        buffer.put(x);
        buffer.put(y);
        buffer.put(z);
        buffer.put(w);
    }
    
    public void putToBuffer(int offset, FloatBuffer buffer) {
        buffer.put(offset, x);
        buffer.put(offset + 1, y);
        buffer.put(offset + 2, z);
        buffer.put(offset + 3, w);
    }

    public Vec4 addSelf(Vec4 v) {
        this.x += v.x;
        this.y += v.y;
        this.z += v.z;
        this.w += v.w;
        return this;
    }

    public Vec4 add(Vec4 v) {
        return add(new Vec4(), v);
    }

    public Vec4 add(Vec4 out, Vec4 v) {
        out.x = this.x + v.x;
        out.y = this.y + v.y;
        out.z = this.z + v.z;
        out.w = this.w + v.w;
        return out;
    }

    public Vec4 subSelf(Vec4 v) {
        this.x -= v.x;
        this.y -= v.y;
        this.z -= v.z;
        this.w -= v.w;
        return this;
    }

    public Vec4 sub(Vec4 v) {
        return sub(new Vec4(), v);
    }

    public Vec4 sub(Vec4 out, Vec4 v) {
        out.x = this.x - v.x;
        out.y = this.y - v.y;
        out.z = this.z - v.z;
        out.w = this.w - v.w;
        return out;
    }

    public Vec4 mulSelf(float s) {
        this.x *= s;
        this.y *= s;
        this.z *= s;
        this.w *= s;
        return this;
    }

    public Vec4 muls(float s) {
        return muls(new Vec4(), s);
    }

    public Vec4 muls(Vec4 out, float s) {
        out.x = this.x * s;
        out.y = this.y * s;
        out.z = this.z * s;
        out.w = this.w * s;
        return out;
    }

    public Vec4 negateSelf() {
        x = -x;
        y = -y;
        z = -z;
        w = -w;
        return this;
    }

    public Vec4 negate() {
        return negate(new Vec4());
    }

    public Vec4 negate(Vec4 out) {
        out.x = -x;
        out.y = -y;
        out.z = -z;
        out.w = -w;
        return out;
    }

    public Vec4 mulcSelf(Vec4 v) {
        this.x *= v.x;
        this.y *= v.y;
        this.z *= v.z;
        this.w *= v.w;
        return this;
    }

    public Vec4 mulc(Vec4 v) {
        return mulc(new Vec4(), v);
    }

    public Vec4 mulc(Vec4 out, Vec4 v) {
        out.x = this.x * v.x;
        out.y = this.y * v.y;
        out.z = this.z * v.z;
        out.w = this.w * v.w;
        return out;
    }

    public float dot(Vec4 v) {
        return this.x * v.x + this.y * v.y + this.z * v.z + this.w * v.w;
    }

    public float lengthSqr() {
        return x * x + y * y + z * z + w * w;
    }

    public float length() {
        return (float) Math.sqrt(lengthSqr());
    }

    public Vec4 normalizeSelf() {
        float oneOverLength = 1.0f / length();
        this.x *= oneOverLength;
        this.y *= oneOverLength;
        this.z *= oneOverLength;
        this.w *= oneOverLength;
        return this;
    }

    public Vec4 normalize() {
        return normalize(new Vec4());
    }

    public Vec4 normalize(Vec4 out) {
        float oneOverLength = 1.0f / length();
        out.x = x * oneOverLength;
        out.y = y * oneOverLength;
        out.z = z * oneOverLength;
        out.w = w * oneOverLength;
        return out;
    }

    public Vec4 homogenizeSelf() {
        this.x /= w;
        this.y /= w;
        this.z /= w;
        this.w = 1.0f;
        return this;
    }

    public Vec4 homogenize() {
        return homogenize(new Vec4());
    }

    public Vec4 homogenize(Vec4 out) {
        out.x = x / w;
        out.y = y / w;
        out.z = z / w;
        out.w = 1.0f;
        return out;
    }

    public boolean epsilonEquals(Vec4 other, float epsilon) {
        return Math.abs(x - other.x) <= epsilon &&
                Math.abs(y - other.y) <= epsilon &&
                Math.abs(z - other.z) <= epsilon &&
                Math.abs(w - other.w) <= epsilon;
    }

    @Override
    public String toString() {
        return "Vec4{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", w=" + w +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vec4 vec4 = (Vec4) o;
        return Float.compare(x, vec4.x) == 0 && Float.compare(y, vec4.y) == 0 && Float.compare(z, vec4.z) == 0 && Float.compare(w, vec4.w) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, w);
    }

    public static Vec4 unitX() {
        return new Vec4(1, 0, 0, 0);
    }

    public static Vec4 unitY() {
        return new Vec4(0, 1, 0, 0);
    }

    public static Vec4 unitZ() {
        return new Vec4(0, 0, 1, 0);
    }

    public static Vec4 unitW() {
        return new Vec4(0, 0, 0, 1);
    }
}
