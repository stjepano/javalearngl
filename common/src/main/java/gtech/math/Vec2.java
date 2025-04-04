package gtech.math;

import java.nio.FloatBuffer;
import java.util.Objects;

public final class Vec2 {
    public float x, y;
    
    public Vec2() {
        this(0, 0);
    }
    
    public Vec2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vec2(Vec2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    public Vec2(float[] v) {
        this(v, 0);
    }

    public Vec2(float[] v, int offset) {
        this.x = v[offset];
        this.y = v[offset + 1];
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vec2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    public void set(float[] v) {
        set(v, 0);
    }

    public void set(float[] v, int offset) {
        this.x = v[offset];
        this.y = v[offset + 1];
    }

    public float[] toArray() {
        return toArray(new float[2]);
    }

    public float[] toArray(float[] out) {
        return toArray(out, 0);
    }

    public float[] toArray(float[] out, int offset) {
        out[offset] = x;
        out[offset + 1] = y;
        return out;
    }

    /** Puts components to buffer, modifies buffer position. */
    public void putToBuffer(FloatBuffer buffer) {
        buffer.put(x);
        buffer.put(y);
    }

    /** Put two components into float buffer at specified offset, does not modify buffer position */
    public void putToBuffer(int offset, FloatBuffer buffer) {
        buffer.put(offset, x);
        buffer.put(offset + 1, y);
    }

    /** Add v to this vector and return reference to this. */
    public Vec2 addSelf(Vec2 v) {
        x += v.x;
        y += v.y;
        return this;
    }
    
    /** Return c = this + v */
    public Vec2 add(Vec2 v) {
        return add(new Vec2(), v);
    }
    
    /** Return out = this + c */
    public Vec2 add(Vec2 out, Vec2 v) {
        out.x = x + v.x;
        out.y = y + v.y;
        return out;
    }

    /** Return this -= v */
    public Vec2 subSelf(Vec2 v) {
        x -= v.x;
        y -= v.y;
        return this;
    }

    /** Return c = this - v */
    public Vec2 sub(Vec2 v) {
        return sub(new Vec2(), v);
    }

    /** Return out = this - c */
    public Vec2 sub(Vec2 out, Vec2 v) {
        out.x = x - v.x;
        out.y = y - v.y;
        return out;
    }

    /** Return this *= s */
    public Vec2 mulSelf(float s) {
        x *= s;
        y *= s;
        return this;
    }

    /** Return c = this * s */
    public Vec2 muls(float s) {
        return muls(new Vec2(), s);
    }

    /** Return out = this * s */
    public Vec2 muls(Vec2 out, float s) {
        out.x = x * s;
        out.y = y * s;
        return out;
    }

    public Vec2 negateSelf() {
        x = -x;
        y = -y;
        return this;
    }

    public Vec2 negate() {
        return negate(new Vec2());
    }

    public Vec2 negate(Vec2 out) {
        out.x = -x;
        out.y = -y;
        return out;
    }

    public Vec2 mulcSelf(Vec2 v) {
        x *= v.x;
        y *= v.y;
        return this;
    }

    public Vec2 mulc(Vec2 v) {
        return mulc(new Vec2(), v);
    }

    public Vec2 mulc(Vec2 out, Vec2 v) {
        out.x = x * v.x;
        out.y = y * v.y;
        return out;
    }

    /**
     * Rotate this vector by a complex number and return this vector
     */
    public Vec2 rotateSelf(Complex rotation) {
        float newX = x * rotation.real - y * rotation.imaginary;
        float newY = x * rotation.imaginary + y * rotation.real;

        x = newX;
        y = newY;

        return this;
    }

    /**
     * Create a new Vec2 that is this vector rotated by the complex number
     */
    public Vec2 rotate(Complex rotation) {
        return rotate(new Vec2(), rotation);
    }

    /**
     * Rotate this vector by the complex number and store the result in out
     */
    public Vec2 rotate(Vec2 out, Complex rotation) {
        out.x = x * rotation.real - y * rotation.imaginary;
        out.y = x * rotation.imaginary + y * rotation.real;

        return out;
    }

    public Vec2 rotateSelf(float angleRadians) {
        float cos = (float)Math.cos(angleRadians);
        float sin = (float)Math.sin(angleRadians);

        float newX = x * cos - y * sin;
        float newY = x * sin + y * cos;

        this.x = newX;
        this.y = newY;

        return this;
    }

    public Vec2 rotate(float angleRadians) {
        return rotate(new Vec2(), angleRadians);
    }

    public Vec2 rotate(Vec2 out, float angleRadians) {
        float cos = (float)Math.cos(angleRadians);
        float sin = (float)Math.sin(angleRadians);

        out.x = x * cos - y * sin;
        out.y = x * sin + y * cos;

        return out;
    }

    public float dot(Vec2 v) {
        return x * v.x + y * v.y;
    }

    public float lengthSqr() {
        return this.dot(this);
    }

    public float length() {
        return (float)Math.sqrt(lengthSqr());
    }

    public Vec2 normalizeSelf() {
        float oneOveLength = 1.0f / length();
        x *= oneOveLength;
        y *= oneOveLength;
        return this;
    }

    public Vec2 normalize() {
        return normalize(new Vec2());
    }

    public Vec2 normalize(Vec2 out) {
        float l = length();
        out.x = x / l;
        out.y = y / l;
        return out;
    }

    public boolean epsilonEquals(Vec2 other, float epsilon) {
        return Math.abs(x - other.x) <= epsilon &&
                Math.abs(y - other.y) <= epsilon;
    }

    @Override
    public String toString() {
        return "Vec2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vec2 vec2 = (Vec2) o;
        return Float.compare(x, vec2.x) == 0 && Float.compare(y, vec2.y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public static Vec2 unitX() {
        return new Vec2(1, 0);
    }

    public static Vec2 unitY() {
        return new Vec2(0, 1);
    }

    public static void direction(Vec2 out, Vec2 source, Vec2 target) {
        target.sub(out, source);
        out.normalizeSelf();
    }
}
