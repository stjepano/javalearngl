package gtech.math;

import java.nio.FloatBuffer;
import java.util.Objects;

public final class Vec3 {
    public float x, y, z;
    
    public Vec3() {
        this(0, 0, 0);
    }
    
    public Vec3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Vec3(Vec3 other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }
    
    public Vec3(Vec2 v, float z) {
        this.x = v.x;
        this.y = v.y;
        this.z = z;
    }
    
    public Vec3(float[] array) {
        this(array, 0);
    }
    
    public Vec3(float[] array, int offset) {
        this.x = array[offset];
        this.y = array[offset + 1];
        this.z = array[offset + 2];
    }

    public static Vec3 of(float x, float y, float z) {
        return new Vec3(x, y, z);
    }

    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void set(Vec3 other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }

    public void set(Vec2 v, float z) {
        this.x = v.x;
        this.y = v.y;
        this.z = z;
    }

    public void set(float[] array) {
        set(array, 0);
    }

    public void set(float[] array, int offset) {
        this.x = array[offset];
        this.y = array[offset + 1];
        this.z = array[offset + 2];
    }
    
    public float[] toArray() {
        return toArray(new float[3]);
    }

    public float[] toArray(float[] out) {
        return toArray(out, 0);
    }

    public float[] toArray(float[] out, int offset) {
        out[offset] = x;
        out[offset + 1] = y;
        out[offset + 2] = z;
        return out;
    }

    public void putToBuffer(FloatBuffer buffer) {
        buffer.put(x);
        buffer.put(y);
        buffer.put(z);
    }

    public void putToBuffer(int offset, FloatBuffer buffer) {
        buffer.put(offset, x);
        buffer.put(offset + 1, y);
        buffer.put(offset + 2, z);
    }

    public Vec3 addSelf(Vec3 other) {
        this.x += other.x;
        this.y += other.y;
        this.z += other.z;
        return this;
    }

    public Vec3 add(Vec3 other) {
        return add(new Vec3(), other);
    }

    public Vec3 add(Vec3 out, Vec3 other) {
        out.x = this.x + other.x;
        out.y = this.y + other.y;
        out.z = this.z + other.z;
        return out;
    }

    public Vec3 subSelf(Vec3 other) {
        this.x -= other.x;
        this.y -= other.y;
        this.z -= other.z;
        return this;
    }

    public Vec3 sub(Vec3 other) {
        return sub(new Vec3(), other);
    }

    public Vec3 sub(Vec3 out, Vec3 other) {
        out.x = this.x - other.x;
        out.y = this.y - other.y;
        out.z = this.z - other.z;
        return out;
    }

    public Vec3 mulsSelf(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;
        return this;
    }

    public Vec3 muls(float scalar) {
        return muls(new Vec3(), scalar);
    }

    public Vec3 muls(Vec3 out, float scalar) {
        out.x = this.x * scalar;
        out.y = this.y * scalar;
        out.z = this.z * scalar;
        return out;
    }

    public Vec3 negateSelf() {
        this.x = -x;
        this.y = -y;
        this.z = -z;
        return this;
    }

    public Vec3 negate() {
        return negate(new Vec3());
    }

    public Vec3 negate(Vec3 out) {
        out.x = -x;
        out.y = -y;
        out.z = -z;
        return out;
    }

    public Vec3 mulcSelf(Vec3 other) {
        this.x *= other.x;
        this.y *= other.y;
        this.z *= other.z;
        return this;
    }

    public Vec3 mulc(Vec3 other) {
        return mulc(new Vec3(), other);
    }

    public Vec3 mulc(Vec3 out, Vec3 other) {
        out.x = this.x * other.x;
        out.y = this.y * other.y;
        out.z = this.z * other.z;
        return out;
    }

    public Vec3 rotateSelf(Quaternion q) {
        float qx = q.x;
        float qy = q.y;
        float qz = q.z;
        float qw = q.w;

        float oneOverLengthSqr = 1.0f / q.lengthSqr();
        float iqx = -qx * oneOverLengthSqr;
        float iqy = -qy * oneOverLengthSqr;
        float iqz = -qz * oneOverLengthSqr;
        float iqw = qw * oneOverLengthSqr;

        float vx = this.x;
        float vy = this.y;
        float vz = this.z;

        float tx = qw * vx + qy * vz - qz * vy;
        float ty = qw * vy - qx * vz + qz * vx;
        float tz = qw * vz + qx * vy - qy * vx;
        float tw = -qx * vx - qy * vy - qz * vz;

        this.x = tw * iqx + tx * iqw + ty * iqz - tz * iqy;
        this.y = tw * iqy - tx * iqz + ty * iqw + tz * iqx;
        this.z = tw * iqz + tx * iqy - ty * iqx + tz * iqw;

        return this;
    }

    public Vec3 rotate(Quaternion q) {
        return rotate(new Vec3(), q);
    }

    public Vec3 rotate(Vec3 out, Quaternion q) {
        float qx = q.x;
        float qy = q.y;
        float qz = q.z;
        float qw = q.w;

        float oneOverLengthSqr = 1.0f / q.lengthSqr();
        float iqx = -qx * oneOverLengthSqr;
        float iqy = -qy * oneOverLengthSqr;
        float iqz = -qz * oneOverLengthSqr;
        float iqw = qw * oneOverLengthSqr;

        float vx = out.x;
        float vy = out.y;
        float vz = out.z;

        float tx = qw * vx + qy * vz - qz * vy;
        float ty = qw * vy - qx * vz + qz * vx;
        float tz = qw * vz + qx * vy - qy * vx;
        float tw = -qx * vx - qy * vy - qz * vz;

        out.x = tw * iqx + tx * iqw + ty * iqz - tz * iqy;
        out.y = tw * iqy - tx * iqz + ty * iqw + tz * iqx;
        out.z = tw * iqz + tx * iqy - ty * iqx + tz * iqw;

        return out;
    }

    public Vec3 rotateSelf(float angleRadians, float ax, float ay, float az) {
        float sinHalfAngle = (float) Math.sin(angleRadians * 0.5);
        float cosHalfAngle = (float) Math.cos(angleRadians * 0.5);

        float qx = sinHalfAngle * ax;
        float qy = sinHalfAngle * ay;
        float qz = sinHalfAngle * az;
        float qw = cosHalfAngle;

        float lengthSqr = qx * qx + qy * qy + qz * qz + qw * qw;

        float oneOverLengthSqr = 1.0f / lengthSqr;
        float iqx = -qx * oneOverLengthSqr;
        float iqy = -qy * oneOverLengthSqr;
        float iqz = -qz * oneOverLengthSqr;
        float iqw = qw * oneOverLengthSqr;

        float vx = this.x;
        float vy = this.y;
        float vz = this.z;

        float tx = qw * vx + qy * vz - qz * vy;
        float ty = qw * vy - qx * vz + qz * vx;
        float tz = qw * vz + qx * vy - qy * vx;
        float tw = -qx * vx - qy * vy - qz * vz;

        this.x = tw * iqx + tx * iqw + ty * iqz - tz * iqy;
        this.y = tw * iqy - tx * iqz + ty * iqw + tz * iqx;
        this.z = tw * iqz + tx * iqy - ty * iqx + tz * iqw;

        return this;
    }

    public Vec3 rotate(float angleRadians, float ax, float ay, float az) {
        return rotate(new Vec3(), angleRadians, ax, ay, az);
    }

    public Vec3 rotate(Vec3 out, float angleRadians, float ax, float ay, float az) {
        float sinHalfAngle = (float) Math.sin(angleRadians * 0.5);
        float cosHalfAngle = (float) Math.cos(angleRadians * 0.5);

        float qx = sinHalfAngle * ax;
        float qy = sinHalfAngle * ay;
        float qz = sinHalfAngle * az;
        float qw = cosHalfAngle;

        float lengthSqr = qx * qx + qy * qy + qz * qz + qw * qw;

        float oneOverLengthSqr = 1.0f / lengthSqr;
        float iqx = -qx * oneOverLengthSqr;
        float iqy = -qy * oneOverLengthSqr;
        float iqz = -qz * oneOverLengthSqr;
        float iqw = qw * oneOverLengthSqr;

        float vx = out.x;
        float vy = out.y;
        float vz = out.z;

        float tx = qw * vx + qy * vz - qz * vy;
        float ty = qw * vy - qx * vz + qz * vx;
        float tz = qw * vz + qx * vy - qy * vx;
        float tw = -qx * vx - qy * vy - qz * vz;

        out.x = tw * iqx + tx * iqw + ty * iqz - tz * iqy;
        out.y = tw * iqy - tx * iqz + ty * iqw + tz * iqx;
        out.z = tw * iqz + tx * iqy - ty * iqx + tz * iqw;

        return out;

    }

    public Vec3 crossSelf(Vec3 other) {
        float newX = this.y * other.z - this.z * other.y;
        float newY = this.z * other.x - this.x * other.z;
        float newZ = this.x * other.y - this.y * other.x;

        this.x = newX;
        this.y = newY;
        this.z = newZ;

        return this;
    }

    public Vec3 cross(Vec3 other) {
        return cross(new Vec3(), other);
    }

    public Vec3 cross(Vec3 out, Vec3 other) {
        out.x = this.y * other.z - this.z * other.y;
        out.y = this.z * other.x - this.x * other.z;
        out.z = this.x * other.y - this.y * other.x;
        return out;
    }

    public float dot(Vec3 other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    public float length() {
        return (float) Math.sqrt(lengthSqr());
    }

    public float lengthSqr() {
        return x * x + y * y + z * z;
    }

    public Vec3 normalizeSelf() {
        float oneOverLength = 1.0f / length();
        this.x *= oneOverLength;
        this.y *= oneOverLength;
        this.z *= oneOverLength;
        return this;
    }

    public Vec3 normalize() {
        return normalize(new Vec3());
    }

    public Vec3 normalize(Vec3 out) {
        float oneOverLength = 1.0f / length();
        out.x = x * oneOverLength;
        out.y = y * oneOverLength;
        out.z = z * oneOverLength;
        return out;
    }

    public boolean epsilonEquals(Vec3 other, float epsilon) {
        return Math.abs(x - other.x) <= epsilon &&
                Math.abs(y - other.y) <= epsilon &&
                Math.abs(z - other.z) <= epsilon;
    }

    @Override
    public String toString() {
        return "Vec3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vec3 vec3 = (Vec3) o;
        return Float.compare(x, vec3.x) == 0 && Float.compare(y, vec3.y) == 0 && Float.compare(z, vec3.z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    public static Vec3 unitX() {
        return new Vec3(1, 0, 0);
    }

    public static Vec3 unitY() {
        return new Vec3(0, 1, 0);
    }

    public static Vec3 unitZ() {
        return new Vec3(0, 0, 1);
    }

    /** Get the direction from source to target (unit vector) */
    public static void direction(Vec3 out, Vec3 source, Vec3 target) {
        target.sub(out, source);
        out.normalizeSelf();
    }

}
