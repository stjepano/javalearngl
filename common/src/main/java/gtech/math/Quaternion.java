package gtech.math;

import java.nio.FloatBuffer;
import java.util.Objects;

public final class Quaternion {
    public float x, y, z, w;

    public Quaternion() {
        this(0, 0, 0, 1);
    }

    public Quaternion(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Quaternion(float[] array) {
        this(array, 0);
    }

    public Quaternion(float[] array, int offset) {
        x = array[offset];
        y = array[offset + 1];
        z = array[offset + 2];
        w = array[offset + 3];
    }

    public static Quaternion ofAngleAxis(float angleRadians, float x, float y, float z) {
        Quaternion q = new Quaternion();
        q.setAngleAxis(angleRadians, x, y, z);
        return q;
    }

    public static Quaternion ofAngleAxis(float angleRadians, Vec3 axis) {
        return ofAngleAxis(angleRadians, axis.x, axis.y, axis.z);
    }

    public void set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public void set(float[] array) {
        set(array, 0);
    }

    public void set(float[] array, int offset) {
        x = array[offset];
        y = array[offset + 1];
        z = array[offset + 2];
        w = array[offset + 3];
    }

    /**
     * Represent rotation about axis (should be unit vector).
     */
    public void setAngleAxis(float angleRadians, float x, float y, float z) {
        float sinHalfAngle = (float) Math.sin(angleRadians * 0.5);
        float cosHalfAngle = (float) Math.cos(angleRadians * 0.5);

        this.x = sinHalfAngle * x;
        this.y = sinHalfAngle * y;
        this.z = sinHalfAngle * z;
        this.w = cosHalfAngle;
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
        array[offset + 2] = z;
        array[offset + 3] = w;
        return array;
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

    public Quaternion mulSelf(Quaternion q) {
        float newX = this.w * q.x + this.x * q.w + this.y * q.z - this.z * q.y;
        float newY = this.w * q.y - this.x * q.z + this.y * q.w + this.z * q.x;
        float newZ = this.w * q.z + this.x * q.y - this.y * q.x + this.z * q.w;
        float newW = this.w * q.w - this.x * q.x - this.y * q.y - this.z * q.z;

        this.x = newX;
        this.y = newY;
        this.z = newZ;
        this.w = newW;

        return this;
    }

    public Quaternion mul(Quaternion q) {
        return mul(new Quaternion(), q);
    }

    public Quaternion mul(Quaternion out, Quaternion q) {
        out.x = this.w * q.x + this.x * q.w + this.y * q.z - this.z * q.y;
        out.y = this.w * q.y - this.x * q.z + this.y * q.w + this.z * q.x;
        out.z = this.w * q.z + this.x * q.y - this.y * q.x + this.z * q.w;
        out.w = this.w * q.w - this.x * q.x - this.y * q.y - this.z * q.z;
        return out;
    }

    public Quaternion rotateSelf(float angleRadians, Vec3 rotationAxis) {
        return rotateSelf(angleRadians, rotationAxis.x, rotationAxis.y, rotationAxis.z);
    }

    public Quaternion rotate(float angleRadians, Vec3 rotationAxis) {
        return rotate(angleRadians, rotationAxis.x, rotationAxis.y, rotationAxis.z);
    }

    public Quaternion rotate(Quaternion out, float angleRadians, Vec3 rotationAxis) {
        return rotate(out, angleRadians, rotationAxis.x, rotationAxis.y, rotationAxis.z);
    }

    public Quaternion rotateSelf(float angleRadians, float axisX, float axisY, float axisZ) {
        float halfAngle = angleRadians * 0.5f;
        float sinHalfAngle = (float) Math.sin(halfAngle);
        float cosHalfAngle = (float) Math.cos(halfAngle);

        float qx = axisX * sinHalfAngle;
        float qy = axisY * sinHalfAngle;
        float qz = axisZ * sinHalfAngle;
        float qw = cosHalfAngle;

        float newX = w * qx + x * qw + y * qz - z * qy;
        float newY = w * qy - x * qz + y * qw + z * qx;
        float newZ = w * qz + x * qy - y * qx + z * qw;
        float newW = w * qw - x * qx - y * qy - z * qz;

        this.x = newX;
        this.y = newY;
        this.z = newZ;
        this.w = newW;

        return this;
    }

    public Quaternion rotate(float angleRadians, float axisX, float axisY, float axisZ) {
        return rotate(new Quaternion(), angleRadians, axisX, axisY, axisZ);
    }

    public Quaternion rotate(Quaternion out, float angleRadians, float axisX, float axisY, float axisZ) {
        float halfAngle = angleRadians * 0.5f;
        float sinHalfAngle = (float) Math.sin(halfAngle);
        float cosHalfAngle = (float) Math.cos(halfAngle);

        float qx = axisX * sinHalfAngle;
        float qy = axisY * sinHalfAngle;
        float qz = axisZ * sinHalfAngle;
        float qw = cosHalfAngle;

        out.x = w * qx + x * qw + y * qz - z * qy;
        out.y = w * qy - x * qz + y * qw + z * qx;
        out.z = w * qz + x * qy - y * qx + z * qw;
        out.w = w * qw - x * qx - y * qy - z * qz;

        return out;

    }

    public Quaternion mulsSelf(float s) {
        x *= s;
        y *= s;
        z *= s;
        w *= s;
        return this;
    }

    public Quaternion muls(float s) {
        return muls(new Quaternion(), s);
    }

    public Quaternion muls(Quaternion out, float s) {
        out.x = s * x;
        out.y = s * y;
        out.z = s * z;
        out.w = s * w;
        return out;
    }

    public Quaternion conjugateSelf() {
        x = -x;
        y = -y;
        z = -z;
        return this;
    }

    public Quaternion conjugate() {
        return conjugate(new Quaternion());
    }

    public Quaternion conjugate(Quaternion out) {
        out.x = -x;
        out.y = -y;
        out.z = -z;
        out.w = w;
        return out;
    }

    public Quaternion inverseSelf() {
        float oneOverLengthSqr = 1.0f / lengthSqr();
        conjugateSelf();
        mulsSelf(oneOverLengthSqr);
        return this;
    }

    public Quaternion inverse() {
        return inverse(new Quaternion());
    }

    public Quaternion inverse(Quaternion out) {
        conjugate(out);
        out.mulsSelf(1.0f / lengthSqr());
        return out;
    }

    public Vec3 rotate(Vec3 v) {
        return rotate(new Vec3(), v);
    }

    /**
     * Rotate vector using following formula: out = (q * v * q^(-1)).xyz
     */
    public Vec3 rotate(Vec3 out, Vec3 v) {
        float qx = this.x;
        float qy = this.y;
        float qz = this.z;
        float qw = this.w;

        float oneOverLengthSqr = 1.0f / lengthSqr();
        float iqx = -qx * oneOverLengthSqr;
        float iqy = -qy * oneOverLengthSqr;
        float iqz = -qz * oneOverLengthSqr;
        float iqw = qw * oneOverLengthSqr;

        float vx = v.x;
        float vy = v.y;
        float vz = v.z;

        float tx = qw * vx + qy * vz - qz * vy;
        float ty = qw * vy - qx * vz + qz * vx;
        float tz = qw * vz + qx * vy - qy * vx;
        float tw = -qx * vx - qy * vy - qz * vz;

        out.x = tw * iqx + tx * iqw + ty * iqz - tz * iqy;
        out.y = tw * iqy - tx * iqz + ty * iqw + tz * iqx;
        out.z = tw * iqz + tx * iqy - ty * iqx + tz * iqw;

        return out;
    }

    public Vec3 rotatec(Vec3 v) {
        return rotatec(new Vec3(), v);
    }

    /**
     * Rotate vector using following formula: out = (q * v * conj(q)).xyz. Unlike normal rotate method where inverse
     * is calculated, this method should be used only when your quaternion is of unit length.
     */
    public Vec3 rotatec(Vec3 out, Vec3 v) {
        float qx = this.x;
        float qy = this.y;
        float qz = this.z;
        float qw = this.w;

        float iqx = -qx;
        float iqy = -qy;
        float iqz = -qz;
        float iqw = qw;

        float vx = v.x;
        float vy = v.y;
        float vz = v.z;

        float tx = qw * vx + qy * vz - qz * vy;
        float ty = qw * vy - qx * vz + qz * vx;
        float tz = qw * vz + qx * vy - qy * vx;
        float tw = -qx * vx - qy * vy - qz * vz;

        out.x = tw * iqx + tx * iqw + ty * iqz - tz * iqy;
        out.y = tw * iqy - tx * iqz + ty * iqw + tz * iqx;
        out.z = tw * iqz + tx * iqy - ty * iqx + tz * iqw;

        return out;
    }

    public float lengthSqr() {
        return x * x + y * y + z * z + w * w;
    }

    public float length() {
        return (float) Math.sqrt(lengthSqr());
    }

    public float dot(Quaternion q) {
        return x * q.x + y * q.y + z * q.z + w * q.w;
    }

    /** Calculate angle (which is returned) and axis */
    public float getAngleAxis(Vec3 outAxis) {
        float angle = 2.0f * (float) Math.acos(w);
        if (Math.abs(angle) < 1e-6f) {
            angle = 0.0f;
            outAxis.x = 1.0f;
            outAxis.y = 0.0f;
            outAxis.z = 0.0f;
        } else {
            float sinHalfAngle = (float) Math.sin(angle * 0.5);
            outAxis.x = x / sinHalfAngle;
            outAxis.y = y / sinHalfAngle;
            outAxis.z = z / sinHalfAngle;
        }
        return angle;
    }

    public boolean epsilonEquals(Quaternion other, float epsilon) {
        return Math.abs(x - other.x) <= epsilon && Math.abs(y - other.y) <= epsilon && Math.abs(z - other.z) <= epsilon
                && Math.abs(w - other.w) <= epsilon;
    }

    @Override
    public String toString() {
        return "Quaternion{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", w=" + w +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Quaternion that = (Quaternion) o;
        return Float.compare(x, that.x) == 0 && Float.compare(y, that.y) == 0 && Float.compare(z, that.z) == 0 && Float.compare(w, that.w) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, w);
    }

}
