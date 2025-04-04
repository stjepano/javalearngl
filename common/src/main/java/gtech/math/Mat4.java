package gtech.math;

import java.nio.FloatBuffer;
import java.util.Arrays;
import java.util.Objects;

public final class Mat4 {

    // NOTE: column major order
    public final float[] elements = new float[16];

    /*
       Index order:
       0  4  8  12
       1  5  9  13
       2  6  10 14
       3  7  11 15

       Mathematical order:
       m00  m01  m02  m03
       m10  m11  m12  m13
       m20  m21  m22  m23
       m30  m31  m32  m33
     */


    public Mat4() {
        setZero();
    }

    public Mat4(Mat4 other) {
        set(other);
    }

    public Mat4(float[] elements) {
        System.arraycopy(elements, 0, this.elements, 0, elements.length);
    }

    public Mat4(float[] elements, int offset) {
        System.arraycopy(elements, offset, this.elements, 0, elements.length);
    }

    public Mat4(Vec4 col0, Vec4 col1, Vec4 col2, Vec4 col3) {
        elements[0] = col0.x;
        elements[1] = col0.y;
        elements[2] = col0.z;
        elements[3] = col0.w;

        elements[4] = col1.x;
        elements[5] = col1.y;
        elements[6] = col1.z;
        elements[7] = col1.w;

        elements[8] = col2.x;
        elements[9] = col2.y;
        elements[10] = col2.z;
        elements[11] = col2.w;

        elements[12] = col3.x;
        elements[13] = col3.y;
        elements[14] = col3.z;
        elements[15] = col3.w;
    }

    public static Mat4 identity() {
        Mat4 m = new Mat4();
        m.setIdentity();
        return m;
    }

    public static Mat4 zero() {
        Mat4 m = new Mat4();
        m.setZero();
        return m;
    }

    public static Mat4 ofOrthographic(float left, float right, float bottom, float top, float near, float far) {
        Mat4 m = new Mat4();
        m.setOrthographic(left, right, bottom, top, near, far);
        return m;
    }

    public static Mat4 ofFrustum(float left, float right, float bottom, float top, float near, float far) {
        Mat4 m = new Mat4();
        m.setFrustum(left, right, bottom, top, near, far);
        return m;
    }

    public static Mat4 ofScale(float sx, float sy, float sz) {
        Mat4 m = new Mat4();
        m.setScale(sx, sy, sz);
        return m;
    }

    public static Mat4 ofTranslation(float tx, float ty, float tz) {
        Mat4 m = new Mat4();
        m.setTranslation(tx, ty, tz);
        return m;
    }

    public static Mat4 ofTranslation(Vec3 t) {
        Mat4 m = new Mat4();
        m.setTranslation(t);
        return m;
    }

    public static Mat4 ofRotationX(float angleRadians) {
        Mat4 m = new Mat4();
        m.setRotationX(angleRadians);
        return m;
    }

    public static Mat4 ofRotationY(float angleRadians) {
        Mat4 m = new Mat4();
        m.setRotationY(angleRadians);
        return m;
    }

    public static Mat4 ofRotationZ(float angleRadians) {
        Mat4 m = new Mat4();
        m.setRotationZ(angleRadians);
        return m;
    }

    public static Mat4 ofAngleAxis(float angleRadians, float x, float y, float z) {
        Mat4 m = new Mat4();
        m.setRotation(angleRadians, x, y, z);
        return m;
    }

    public static Mat4 ofQuaternion(Quaternion q) {
        Mat4 m = new Mat4();
        m.setRotation(q);
        return m;
    }

    public static Mat4 ofLookAt(Vec3 position, Vec3 target, Vec3 up) {
        Mat4 m = new Mat4();
        m.setLookAt(position, target, up);
        return m;
    }

    public void set(Mat4 other) {
        System.arraycopy(other.elements, 0, elements, 0, elements.length);
    }

    public void set(Vec4 col0, Vec4 col1, Vec4 col2, Vec4 col3) {
        elements[0] = col0.x;
        elements[1] = col0.y;
        elements[2] = col0.z;
        elements[3] = col0.w;

        elements[4] = col1.x;
        elements[5] = col1.y;
        elements[6] = col1.z;
        elements[7] = col1.w;

        elements[8] = col2.x;
        elements[9] = col2.y;
        elements[10] = col2.z;
        elements[11] = col2.w;

        elements[12] = col3.x;
        elements[13] = col3.y;
        elements[14] = col3.z;
        elements[15] = col3.w;
    }

    public void set(float[] array) {
        set(array, 0);
    }

    public void set(float[] array, int offset) {
        System.arraycopy(array, offset, elements, 0, elements.length);
    }

    public float get(int row, int col) {
        // NOTE: column major order
        return elements[col * 4 + row];
    }

    public void set(int row, int col, float value) {
        // NOTE: column major order
        elements[col * 4 + row] = value;
    }

    public float[] toArray() {
        return toArray(new float[16]);
    }

    public float[] toArray(float[] array) {
        return toArray(array, 0);
    }

    public float[] toArray(float[] array, int offset) {
        System.arraycopy(elements, 0, array, offset, elements.length);
        return array;
    }

    public void putToBuffer(FloatBuffer buffer) {
        buffer.put(elements);
    }

    public void putToBuffer(int offset, FloatBuffer buffer) {
        buffer.put(offset, elements);
    }

    public void setZero() {
        Arrays.fill(elements, 0);
    }

    public void setIdentity() {
        setZero();
        elements[0] = 1;
        elements[5] = 1;
        elements[10] = 1;
        elements[15] = 1;
    }

    public void setOrthographic(float left, float right, float bottom, float top, float near, float far) {
        setZero();
        elements[0] = 2 / (right - left);
        elements[5] = 2 / (top - bottom);
        elements[10] = -2 / (far - near);
        elements[12] = -(right + left) / (right - left);
        elements[13] = -(top + bottom) / (top - bottom);
        elements[14] = -(far + near) / (far - near);
        elements[15] = 1;
    }

    public void setFrustum(float left, float right, float bottom, float top, float near, float far) {
        setZero();
        elements[0] = (2 * near) / (right - left);
        elements[5] = (2 * near) / (top - bottom);
        elements[8] = (right + left) / (right - left);
        elements[9] = (top + bottom) / (top - bottom);
        elements[10] = -(far + near) / (far - near);
        elements[11] = -1;
        elements[14] = -(2 * far * near) / (far - near);
        elements[15] = 0;
    }

    /**
     * Set the matrix to be perspective projection matrix.
     * @param fovRadians vertical field of view in radians
     * @param aspectRatio width / height of viewport
     * @param near distance to near clipping plane
     * @param far distance to far clipping plane
     */
    public void setPerspective(float fovRadians, float aspectRatio, float near, float far) {
        float top = (float) Math.tan(fovRadians / 2) * near;
        float bottom = -top;
        float left = aspectRatio * bottom;
        float right = aspectRatio * top;
        setFrustum(left, right, bottom, top, near, far);
    }

    public void setScale(float sx, float sy, float sz) {
        setIdentity();
        elements[0] = sx;
        elements[5] = sy;
        elements[10] = sz;
    }

    public void setTranslation(float tx, float ty, float tz) {
        setIdentity();
        elements[12] = tx;
        elements[13] = ty;
        elements[14] = tz;
    }

    public void setTranslation(Vec3 t) {
        setTranslation(t.x, t.y, t.z);
    }

    public void setRotationX(float angleRadians) {
        float c = (float) Math.cos(angleRadians);
        float s = (float) Math.sin(angleRadians);
        setIdentity();
        elements[5] = c;
        elements[6] = s;
        elements[9] = -s;
        elements[10] = c;
    }

    public void setRotationY(float angleRadians) {
        float c = (float) Math.cos(angleRadians);
        float s = (float) Math.sin(angleRadians);
        setIdentity();
        elements[0] = c;
        elements[2] = -s;
        elements[8] = s;
        elements[10] = c;
    }

    public void setRotationZ(float angleRadians) {
        float c = (float) Math.cos(angleRadians);
        float s = (float) Math.sin(angleRadians);
        setIdentity();
        elements[0] = c;
        elements[1] = s;
        elements[4] = -s;
        elements[5] = c;
    }

    public void setRotation(float angleRadians, float x, float y, float z) {
        float c = (float) Math.cos(angleRadians);
        float s = (float) Math.sin(angleRadians);

        elements[0] = c + x * x * (1 - c);
        elements[1] = x * y * (1 - c) + z * s;
        elements[2] = x * z * (1 - c) - y * s;
        elements[3] = 0;

        elements[4] = x * y * (1 - c) - z * s;
        elements[5] = c + y * y * (1 - c);
        elements[6] = y * z * (1 - c) + x * s;
        elements[7] = 0;

        elements[8] = x * z * (1 - c) + y * s;
        elements[9] = y * z * (1 - c) - x * s;
        elements[10] = c + z * z * (1 - c);
        elements[11] = 0;

        elements[12] = 0;
        elements[13] = 0;
        elements[14] = 0;
        elements[15] = 1;
    }

    public void setRotation(Quaternion q) {
        elements[0] = 1 - 2 * q.y * q.y - 2 * q.z * q.z;
        elements[1] = 2 * q.x * q.y + 2 * q.z * q.w;
        elements[2] = 2 * q.x * q.z - 2 * q.y * q.w;
        elements[3] = 0;

        elements[4] = 2 * q.x * q.y - 2 * q.z * q.w;
        elements[5] = 1 - 2 * q.x * q.x - 2 * q.z * q.z;
        elements[6] = 2 * q.y * q.z + 2 * q.x * q.w;
        elements[7] = 0;

        elements[8] = 2 * q.x * q.z + 2 * q.y * q.w;
        elements[9] = 2 * q.y * q.z - 2 * q.x * q.w;
        elements[10] = 1 - 2 * q.x * q.x - 2 * q.y * q.y;
        elements[11] = 0;

        elements[12] = 0;
        elements[13] = 0;
        elements[14] = 0;
        elements[15] = 1;
    }

    public void setLookAt(Vec3 position, Vec3 target, Vec3 up) {
        Vec3 direction = new Vec3();
        Vec3.direction(direction, position, target);
        direction.negateSelf();
        Vec3 right = up.cross(direction);

        elements[0] = right.x;
        elements[1] = up.x;
        elements[2] = direction.x;
        elements[3] = 0;

        elements[4] = right.y;
        elements[5] = up.y;
        elements[6] = direction.y;
        elements[7] = 0;

        elements[8] = right.z;
        elements[9] = up.z;
        elements[10] = direction.z;
        elements[11] = 0;

        elements[12] = -position.x * right.x - position.y * right.y - position.z * right.z;
        elements[13] = -position.x * up.x - position.y * up.y - position.z * up.z;
        elements[14] = -position.x * direction.x - position.y * direction.y - position.z * direction.z;
        elements[15] = 1;
    }

    public Mat4 transposeSelf() {
        final float el4 = elements[4];
        final float el8 = elements[8];
        final float el12 = elements[12];
        final float el1 = elements[1];
        final float el9 = elements[9];
        final float el13 = elements[13];
        final float el2 = elements[2];
        final float el6 = elements[6];
        final float el14 = elements[14];
        final float el3 = elements[3];
        final float el7 = elements[7];
        final float el11 = elements[11];

        elements[1] = el4;
        elements[2] = el8;
        elements[3] = el12;


        elements[4] = el1;
        elements[6] = el9;
        elements[7] = el13;

        elements[8] = el2;
        elements[9] = el6;
        elements[11] = el14;

        elements[12] = el3;
        elements[13] = el7;
        elements[14] = el11;

        return this;
    }

    public Mat4 transpose() {
        return transpose(new Mat4());
    }

    public Mat4 transpose(Mat4 out) {
        out.elements[0] = elements[0];
        out.elements[1] = elements[4];
        out.elements[2] = elements[8];
        out.elements[3] = elements[12];

        out.elements[4] = elements[1];
        out.elements[5] = elements[5];
        out.elements[6] = elements[9];
        out.elements[7] = elements[13];

        out.elements[8] = elements[2];
        out.elements[9] = elements[6];
        out.elements[10] = elements[10];
        out.elements[11] = elements[14];

        out.elements[12] = elements[3];
        out.elements[13] = elements[7];
        out.elements[14] = elements[11];
        out.elements[15] = elements[15];
        return out;
    }

    public Mat4 mulSelf(Mat4 m) {
        final float[] elementsCopy = new float[16];
        System.arraycopy(this.elements, 0, elementsCopy, 0, 16);

        elements[0] = elementsCopy[0] * m.elements[0] + elementsCopy[4] * m.elements[1] + elementsCopy[8] *  m.elements[2] + elementsCopy[12] * m.elements[3];
        elements[1] = elementsCopy[1] * m.elements[0] + elementsCopy[5] * m.elements[1] + elementsCopy[9] *  m.elements[2] + elementsCopy[13] * m.elements[3] ;
        elements[2] = elementsCopy[2] * m.elements[0] + elementsCopy[6] * m.elements[1] + elementsCopy[10]*  m.elements[2] + elementsCopy[14]*  m.elements[3];
        elements[3] = elementsCopy[3] * m.elements[0] + elementsCopy[7] * m.elements[1] + elementsCopy[11]*  m.elements[2] + elementsCopy[15]*  m.elements[3];

        elements[4] = elementsCopy[0] * m.elements[4] + elementsCopy[4] * m.elements[5] + elementsCopy[8] *  m.elements[6] + elementsCopy[12] * m.elements[7] ;
        elements[5] = elementsCopy[1] * m.elements[4] + elementsCopy[5] * m.elements[5] + elementsCopy[9] *  m.elements[6] + elementsCopy[13] * m.elements[7] ;
        elements[6] = elementsCopy[2] * m.elements[4] + elementsCopy[6] * m.elements[5] + elementsCopy[10]*  m.elements[6] + elementsCopy[14] * m.elements[7];
        elements[7] = elementsCopy[3] * m.elements[4] + elementsCopy[7] * m.elements[5] + elementsCopy[11]*  m.elements[6] + elementsCopy[15] * m.elements[7];

        elements[ 8] = elementsCopy[0] * m.elements[8] + elementsCopy[4] * m.elements[9] + elementsCopy[8]  *  m.elements[10] + elementsCopy[12] * m.elements[11] ;
        elements[ 9] = elementsCopy[1] * m.elements[8] + elementsCopy[5] * m.elements[9] + elementsCopy[9]  *  m.elements[10] + elementsCopy[13] * m.elements[11] ;
        elements[10] = elementsCopy[2] * m.elements[8] + elementsCopy[6] * m.elements[9] + elementsCopy[10] *  m.elements[10] + elementsCopy[14]*  m.elements[11];
        elements[11] = elementsCopy[3] * m.elements[8] + elementsCopy[7] * m.elements[9] + elementsCopy[11] *  m.elements[10] + elementsCopy[15]*  m.elements[11];

        elements[12] = elementsCopy[0] * m.elements[12] + elementsCopy[4] * m.elements[13] + elementsCopy[8] *  m.elements[14] + elementsCopy[12] * m.elements[15] ;
        elements[13] = elementsCopy[1] * m.elements[12] + elementsCopy[5] * m.elements[13] + elementsCopy[9] *  m.elements[14] + elementsCopy[13] * m.elements[15] ;
        elements[14] = elementsCopy[2] * m.elements[12] + elementsCopy[6] * m.elements[13] + elementsCopy[10]*  m.elements[14] + elementsCopy[14]*  m.elements[15];
        elements[15] = elementsCopy[3] * m.elements[12] + elementsCopy[7] * m.elements[13] + elementsCopy[11]*  m.elements[14] + elementsCopy[15]*  m.elements[15];
        return this;
    }

    public Mat4 mul(Mat4 m) {
        return mul(new Mat4(), m);
    }

    public Mat4 mul(Mat4 out, Mat4 m) {
        out.elements[0] = elements[0] * m.elements[0] + elements[4] * m.elements[1] + elements[8] * m.elements[2] + elements[12] * m.elements[3];
        out.elements[1] = elements[1] * m.elements[0] + elements[5] * m.elements[1] + elements[9] * m.elements[2] + elements[13] * m.elements[3] ;
        out.elements[2] = elements[2] * m.elements[0] + elements[6] * m.elements[1] + elements[10]*  m.elements[2] + elements[14]*  m.elements[3];
        out.elements[3] = elements[3] * m.elements[0] + elements[7] * m.elements[1] + elements[11]*  m.elements[2] + elements[15]*  m.elements[3];

        out.elements[4] = elements[0] * m.elements[4] + elements[4] * m.elements[5] + elements[8] * m.elements[6] + elements[12] * m.elements[7] ;
        out.elements[5] = elements[1] * m.elements[4] + elements[5] * m.elements[5] + elements[9] * m.elements[6] + elements[13] * m.elements[7] ;
        out.elements[6] = elements[2] * m.elements[4] + elements[6] * m.elements[5] + elements[10]*  m.elements[6] + elements[14]*  m.elements[7];
        out.elements[7] = elements[3] * m.elements[4] + elements[7] * m.elements[5] + elements[11]*  m.elements[6] + elements[15]*  m.elements[7];

        out.elements[ 8] = elements[0] * m.elements[8] + elements[4] * m.elements[9] + elements[8]  * m.elements[10] + elements[12] * m.elements[11] ;
        out.elements[ 9] = elements[1] * m.elements[8] + elements[5] * m.elements[9] + elements[9]  * m.elements[10] + elements[13] * m.elements[11] ;
        out.elements[10] = elements[2] * m.elements[8] + elements[6] * m.elements[9] + elements[10] *  m.elements[10] + elements[14]*  m.elements[11];
        out.elements[11] = elements[3] * m.elements[8] + elements[7] * m.elements[9] + elements[11] *  m.elements[10] + elements[15]*  m.elements[11];

        out.elements[12] = elements[0] * m.elements[12] + elements[4] * m.elements[13] + elements[8] * m.elements[14] + elements[12] * m.elements[15] ;
        out.elements[13] = elements[1] * m.elements[12] + elements[5] * m.elements[13] + elements[9] * m.elements[14] + elements[13] * m.elements[15] ;
        out.elements[14] = elements[2] * m.elements[12] + elements[6] * m.elements[13] + elements[10]*  m.elements[14] + elements[14]*  m.elements[15];
        out.elements[15] = elements[3] * m.elements[12] + elements[7] * m.elements[13] + elements[11]*  m.elements[14] + elements[15]*  m.elements[15];
        return out;
    }

    public Mat4 translateSelf(float x, float y, float z) {
        final float[] elementsCopy = new float[16];
        System.arraycopy(this.elements, 0, elementsCopy, 0, 16);

        elements[12] = elementsCopy[0] * x + elementsCopy[4] * y + elementsCopy[8] * z + elementsCopy[12];
        elements[13] = elementsCopy[1] * x + elementsCopy[5] * y + elementsCopy[9] * z + elementsCopy[13];
        elements[14] = elementsCopy[2] * x + elementsCopy[6] * y + elementsCopy[10]* z + elementsCopy[14];
        elements[15] = elementsCopy[3] * x + elementsCopy[7] * y + elementsCopy[11]* z + elementsCopy[15];

        return this;
    }

    public Mat4 translate(float x, float y, float z) {
        return translate(new Mat4(), x, y, z);
    }

    public Mat4 translate(Mat4 out, float x, float y, float z) {
        out.elements[0] = elements[0];
        out.elements[1] = elements[1];
        out.elements[2] = elements[2];
        out.elements[3] = elements[3];

        out.elements[4] = elements[4];
        out.elements[5] = elements[5];
        out.elements[6] = elements[6];
        out.elements[7] = elements[7];

        out.elements[8] = elements[8];
        out.elements[9] = elements[9];
        out.elements[10] = elements[10];
        out.elements[11] = elements[11];

        out.elements[12] = elements[0] * x + elements[4] * y + elements[8] * z + elements[12];
        out.elements[13] = elements[1] * x + elements[5] * y + elements[9] * z + elements[13];
        out.elements[14] = elements[2] * x + elements[6] * y + elements[10]* z + elements[14];
        out.elements[15] = elements[3] * x + elements[7] * y + elements[11]* z + elements[15];

        return this;
    }

    public Mat4 translateSelf(Vec3 t) {
        return translateSelf(t.x, t.y, t.z);
    }

    public Mat4 translate(Vec3 t) {
        return translate(new Mat4(), t.x, t.y, t.z);
    }

    public Mat4 translate(Mat4 out, Vec3 t) {
        return translate(out, t.x, t.y, t.z);
    }

    public Mat4 scaleSelf(float sx, float sy, float sz) {
        elements[0] *= sx;
        elements[1] *= sx;
        elements[2] *= sx;
        elements[3] *= sx;

        elements[4] *= sy;
        elements[5] *= sy;
        elements[6] *= sy;
        elements[7] *= sy;

        elements[8] *= sz;
        elements[9] *= sz;
        elements[10] *= sz;
        elements[11] *= sz;

        return this;
    }

    public Mat4 scale(float sx, float sy, float sz) {
        return scale(new Mat4(), sx, sy, sz);
    }

    public Mat4 scale(Mat4 out, float sx, float sy, float sz) {
        out.elements[0] = elements[0] * sx;
        out.elements[1] = elements[1] * sx;
        out.elements[2] = elements[2] * sx;
        out.elements[3] = elements[3] * sx;

        out.elements[4] = elements[4] * sy;
        out.elements[5] = elements[5] * sy;
        out.elements[6] = elements[6] * sy;
        out.elements[7] = elements[7] * sy;

        out.elements[8] = elements[8] * sz;
        out.elements[9] = elements[9] * sz;
        out.elements[10] = elements[10] * sz;
        out.elements[11] = elements[11] * sz;

        out.elements[12] = elements[12];
        out.elements[13] = elements[13];
        out.elements[14] = elements[14];
        out.elements[15] = elements[15];

        return out;
    }

    public Mat4 scaleSelf(Vec3 s) {
        return scaleSelf(s.x, s.y, s.z);
    }

    public Mat4 scale(Vec3 s) {
        return scale(new Mat4(), s.x, s.y, s.z);
    }

    public Mat4 scale(Mat4 out, Vec3 s) {
        return scale(out, s.x, s.y, s.z);
    }

    public Mat4 rotateSelf(Quaternion q) {
        float r0 = 1 - 2.0f * q.y * q.y - 2.0f * q.z * q.z;
        float r1 = 2.0f * q.x * q.y + 2.0f * q.z * q.w;
        float r2 = 2.0f * q.x * q.z - 2.0f * q.y * q.w;

        float r4 = 2.0f * q.x * q.y - 2.0f * q.z * q.w;
        float r5 = 1 - 2.0f * q.x * q.x - 2.0f * q.z * q.z;
        float r6 = 2.0f * q.y * q.z + 2.0f * q.x * q.w;

        float r8 = 2.0f * q.x * q.z + 2.0f * q.y * q.w;
        float r9 = 2.0f * q.y * q.z - 2.0f * q.x * q.w;
        float r10 = 1 - 2.0f * q.x * q.x - 2.0f * q.y * q.y;

        float e0 = elements[0] * r0 + elements[4] * r1 + elements[8] * r2;
        float e1 = elements[1] * r0 + elements[5] * r1 + elements[9] * r2;
        float e2 = elements[2] * r0 + elements[6] * r1 + elements[10]* r2;
        float e3 = elements[3] * r0 + elements[7] * r1 + elements[11]* r2;

        float e4 = elements[0] * r4 + elements[4] * r5 + elements[8] * r6;
        float e5 = elements[1] * r4 + elements[5] * r5 + elements[9] * r6;
        float e6 = elements[2] * r4 + elements[6] * r5 + elements[10]* r6;
        float e7 = elements[3] * r4 + elements[7] * r5 + elements[11]* r6;

        float e8 = elements[0] * r8 + elements[4] * r9 + elements[8] * r10;
        float e9 = elements[1] * r8 + elements[5] * r9 + elements[9] * r10;
        float e10 = elements[2] * r8 + elements[6] * r9 + elements[10]* r10;
        float e11 = elements[3] * r8 + elements[7] * r9 + elements[11]* r10;

        elements[0] = e0;
        elements[1] = e1;
        elements[2] = e2;
        elements[3] = e3;

        elements[4] = e4;
        elements[5] = e5;
        elements[6] = e6;
        elements[7] = e7;

        elements[8] = e8;
        elements[9] = e9;
        elements[10] = e10;
        elements[11] = e11;

        return this;
    }

    public Mat4 rotate(Quaternion q) {
        return rotate(new Mat4(), q);
    }

    public Mat4 rotate(Mat4 out, Quaternion q) {
        float r0 = 1 - 2.0f * q.y * q.y - 2.0f * q.z * q.z;
        float r1 = 2.0f * q.x * q.y + 2.0f * q.z * q.w;
        float r2 = 2.0f * q.x * q.z - 2.0f * q.y * q.w;

        float r4 = 2.0f * q.x * q.y - 2.0f * q.z * q.w;
        float r5 = 1 - 2.0f * q.x * q.x - 2.0f * q.z * q.z;
        float r6 = 2.0f * q.y * q.z + 2.0f * q.x * q.w;

        float r8 = 2.0f * q.x * q.z + 2.0f * q.y * q.w;
        float r9 = 2.0f * q.y * q.z - 2.0f * q.x * q.w;
        float r10 = 1 - 2.0f * q.x * q.x - 2.0f * q.y * q.y;

        out.elements[0] = elements[0] * r0 + elements[4] * r1 + elements[8] * r2;
        out.elements[1] = elements[1] * r0 + elements[5] * r1 + elements[9] * r2;
        out.elements[2] = elements[2] * r0 + elements[6] * r1 + elements[10]* r2;
        out.elements[3] = elements[3] * r0 + elements[7] * r1 + elements[11]* r2;

        out.elements[4] = elements[0] * r4 + elements[4] * r5 + elements[8] * r6;
        out.elements[5] = elements[1] * r4 + elements[5] * r5 + elements[9] * r6;
        out.elements[6] = elements[2] * r4 + elements[6] * r5 + elements[10]* r6;
        out.elements[7] = elements[3] * r4 + elements[7] * r5 + elements[11]* r6;

        out.elements[8] = elements[0] * r8 + elements[4] * r9 + elements[8] * r10;
        out.elements[9] = elements[1] * r8 + elements[5] * r9 + elements[9] * r10;
        out.elements[10] = elements[2] * r8 + elements[6] * r9 + elements[10]* r10;
        out.elements[11] = elements[3] * r8 + elements[7] * r9 + elements[11]* r10;

        out.elements[12] = elements[12];
        out.elements[13] = elements[13];
        out.elements[14] = elements[14];
        out.elements[15] = elements[15];

        return out;
    }

    public float determinant() {
        return elements[3] * elements[6] * elements[9] * elements[12] - elements[2] * elements[7] * elements[9] * elements[12] -
                elements[3] * elements[5] * elements[10] * elements[12] + elements[1] * elements[7] * elements[10] * elements[12] +
                elements[2] * elements[5] * elements[11] * elements[12] - elements[1] * elements[6] * elements[11] * elements[12] -
                elements[3] * elements[6] * elements[8] * elements[13] + elements[2] * elements[7] * elements[8] * elements[13] +
                elements[3] * elements[4] * elements[10] * elements[13] - elements[0] * elements[7] * elements[10] * elements[13] -
                elements[2] * elements[4] * elements[11] * elements[13] + elements[0] * elements[6] * elements[11] * elements[13] +
                elements[3] * elements[5] * elements[8] * elements[14] - elements[1] * elements[7] * elements[8] * elements[14] -
                elements[3] * elements[4] * elements[9] * elements[14] + elements[0] * elements[7] * elements[9] * elements[14] +
                elements[1] * elements[4] * elements[11] * elements[14] - elements[0] * elements[5] * elements[11] * elements[14] -
                elements[2] * elements[5] * elements[8] * elements[15] + elements[1] * elements[6] * elements[8] * elements[15] +
                elements[2] * elements[4] * elements[9] * elements[15] - elements[0] * elements[6] * elements[9] * elements[15] -
                elements[1] * elements[4] * elements[10] * elements[15] + elements[0] * elements[5] * elements[10] * elements[15];
    }

    public Mat4 mulsSelf(float s) {
        for (int i = 0; i < elements.length; i++) {
            elements[i] *= s;
        }
        return this;
    }

    public Mat4 muls(float s) {
        return muls(new Mat4(), s);
    }

    public Mat4 muls(Mat4 out, float s) {
        for (int i = 0; i < elements.length; i++) {
            out.elements[i] = elements[i] * s;
        }
        return out;
    }

    public Mat4 inverseSelf() {
        float oneOverDet = 1.0f / determinant();

        final float[] a = new float[16];
        System.arraycopy(elements, 0, a, 0, 16);

        elements[0] = -a[7]*a[10]*a[13] + a[6]*a[11]*a[13] + a[7]*a[9]*a[14] - a[5]*a[11]*a[14] - a[6]*a[9]*a[15] + a[5]*a[10]*a[15];
        elements[1] = a[3]*a[10]*a[13] - a[2]*a[11]*a[13] - a[3]*a[9]*a[14] + a[1]*a[11]*a[14] + a[2]*a[9]*a[15] - a[1]*a[10]*a[15] ;
        elements[2] = -a[3]*a[6]*a[13] + a[2]*a[7]*a[13] + a[3]*a[5]*a[14] - a[1]*a[7]*a[14] - a[2]*a[5]*a[15] + a[1]*a[6]*a[15]    ;
        elements[3] = a[3]*a[6]*a[9] - a[2]*a[7]*a[9] - a[3]*a[5]*a[10] + a[1]*a[7]*a[10] + a[2]*a[5]*a[11] - a[1]*a[6]*a[11]       ;

        elements[4] = a[7]*a[10]*a[12] - a[6]*a[11]*a[12] - a[7]*a[8]*a[14] + a[4]*a[11]*a[14] + a[6]*a[8]*a[15] - a[4]*a[10]*a[15] ;
        elements[5] = -a[3]*a[10]*a[12] + a[2]*a[11]*a[12] + a[3]*a[8]*a[14] - a[0]*a[11]*a[14] - a[2]*a[8]*a[15] + a[0]*a[10]*a[15];
        elements[6] = a[3]*a[6]*a[12] - a[2]*a[7]*a[12] - a[3]*a[4]*a[14] + a[0]*a[7]*a[14] + a[2]*a[4]*a[15] - a[0]*a[6]*a[15]     ;
        elements[7] = -a[3]*a[6]*a[8] + a[2]*a[7]*a[8] + a[3]*a[4]*a[10] - a[0]*a[7]*a[10] - a[2]*a[4]*a[11] + a[0]*a[6]*a[11]      ;

        elements[ 8] = -a[7]*a[9]*a[12] + a[5]*a[11]*a[12] + a[7]*a[8]*a[13] - a[4]*a[11]*a[13] - a[5]*a[8]*a[15] + a[4]*a[9]*a[15];
        elements[ 9] = a[3]*a[9]*a[12] - a[1]*a[11]*a[12] - a[3]*a[8]*a[13] + a[0]*a[11]*a[13] + a[1]*a[8]*a[15] - a[0]*a[9]*a[15] ;
        elements[10] = -a[3]*a[5]*a[12] + a[1]*a[7]*a[12] + a[3]*a[4]*a[13] - a[0]*a[7]*a[13] - a[1]*a[4]*a[15] + a[0]*a[5]*a[15]  ;
        elements[11] = a[3]*a[5]*a[8] - a[1]*a[7]*a[8] - a[3]*a[4]*a[9] + a[0]*a[7]*a[9] + a[1]*a[4]*a[11] - a[0]*a[5]*a[11]       ;

        elements[12] = a[6]*a[9]*a[12] - a[5]*a[10]*a[12] - a[6]*a[8]*a[13] + a[4]*a[10]*a[13] + a[5]*a[8]*a[14] - a[4]*a[9]*a[14] ;
        elements[13] = -a[2]*a[9]*a[12] + a[1]*a[10]*a[12] + a[2]*a[8]*a[13] - a[0]*a[10]*a[13] - a[1]*a[8]*a[14] + a[0]*a[9]*a[14];
        elements[14] = a[2]*a[5]*a[12] - a[1]*a[6]*a[12] - a[2]*a[4]*a[13] + a[0]*a[6]*a[13] + a[1]*a[4]*a[14] - a[0]*a[5]*a[14]   ;
        elements[15] = -a[2]*a[5]*a[8] + a[1]*a[6]*a[8] + a[2]*a[4]*a[9] - a[0]*a[6]*a[9] - a[1]*a[4]*a[10] + a[0]*a[5]*a[10]      ;

        return mulsSelf(oneOverDet);
    }

    public Mat4 inverse() {
        return inverse(new Mat4());
    }

    public Mat4 inverse(Mat4 out) {
        float oneOverDet = 1.0f / determinant();

        final float[] a = elements;

        out.elements[0] = -a[7]*a[10]*a[13] + a[6]*a[11]*a[13] + a[7]*a[9]*a[14] - a[5]*a[11]*a[14] - a[6]*a[9]*a[15] + a[5]*a[10]*a[15];
        out.elements[1] = a[3]*a[10]*a[13] - a[2]*a[11]*a[13] - a[3]*a[9]*a[14] + a[1]*a[11]*a[14] + a[2]*a[9]*a[15] - a[1]*a[10]*a[15] ;
        out.elements[2] = -a[3]*a[6]*a[13] + a[2]*a[7]*a[13] + a[3]*a[5]*a[14] - a[1]*a[7]*a[14] - a[2]*a[5]*a[15] + a[1]*a[6]*a[15]    ;
        out.elements[3] = a[3]*a[6]*a[9] - a[2]*a[7]*a[9] - a[3]*a[5]*a[10] + a[1]*a[7]*a[10] + a[2]*a[5]*a[11] - a[1]*a[6]*a[11]       ;

        out.elements[4] = a[7]*a[10]*a[12] - a[6]*a[11]*a[12] - a[7]*a[8]*a[14] + a[4]*a[11]*a[14] + a[6]*a[8]*a[15] - a[4]*a[10]*a[15] ;
        out.elements[5] = -a[3]*a[10]*a[12] + a[2]*a[11]*a[12] + a[3]*a[8]*a[14] - a[0]*a[11]*a[14] - a[2]*a[8]*a[15] + a[0]*a[10]*a[15];
        out.elements[6] = a[3]*a[6]*a[12] - a[2]*a[7]*a[12] - a[3]*a[4]*a[14] + a[0]*a[7]*a[14] + a[2]*a[4]*a[15] - a[0]*a[6]*a[15]     ;
        out.elements[7] = -a[3]*a[6]*a[8] + a[2]*a[7]*a[8] + a[3]*a[4]*a[10] - a[0]*a[7]*a[10] - a[2]*a[4]*a[11] + a[0]*a[6]*a[11]      ;

        out.elements[ 8] = -a[7]*a[9]*a[12] + a[5]*a[11]*a[12] + a[7]*a[8]*a[13] - a[4]*a[11]*a[13] - a[5]*a[8]*a[15] + a[4]*a[9]*a[15];
        out.elements[ 9] = a[3]*a[9]*a[12] - a[1]*a[11]*a[12] - a[3]*a[8]*a[13] + a[0]*a[11]*a[13] + a[1]*a[8]*a[15] - a[0]*a[9]*a[15] ;
        out.elements[10] = -a[3]*a[5]*a[12] + a[1]*a[7]*a[12] + a[3]*a[4]*a[13] - a[0]*a[7]*a[13] - a[1]*a[4]*a[15] + a[0]*a[5]*a[15]  ;
        out.elements[11] = a[3]*a[5]*a[8] - a[1]*a[7]*a[8] - a[3]*a[4]*a[9] + a[0]*a[7]*a[9] + a[1]*a[4]*a[11] - a[0]*a[5]*a[11]       ;

        out.elements[12] = a[6]*a[9]*a[12] - a[5]*a[10]*a[12] - a[6]*a[8]*a[13] + a[4]*a[10]*a[13] + a[5]*a[8]*a[14] - a[4]*a[9]*a[14] ;
        out.elements[13] = -a[2]*a[9]*a[12] + a[1]*a[10]*a[12] + a[2]*a[8]*a[13] - a[0]*a[10]*a[13] - a[1]*a[8]*a[14] + a[0]*a[9]*a[14];
        out.elements[14] = a[2]*a[5]*a[12] - a[1]*a[6]*a[12] - a[2]*a[4]*a[13] + a[0]*a[6]*a[13] + a[1]*a[4]*a[14] - a[0]*a[5]*a[14]   ;
        out.elements[15] = -a[2]*a[5]*a[8] + a[1]*a[6]*a[8] + a[2]*a[4]*a[9] - a[0]*a[6]*a[9] - a[1]*a[4]*a[10] + a[0]*a[5]*a[10]      ;

        out.mulsSelf(oneOverDet);

        return out;
    }

    public Vec4 mul(Vec4 v) {
        return mul(new Vec4(), v);
    }

    public Vec4 mul(Vec4 out, Vec4 v) {
        out.x = elements[0] * v.x + elements[4] * v.y + elements[8] * v.z + elements[12] * v.w;
        out.y = elements[1] * v.x + elements[5] * v.y + elements[9] * v.z + elements[13] * v.w;
        out.z = elements[2] * v.x + elements[6] * v.y + elements[10] * v.z + elements[14] * v.w;
        out.w = elements[3] * v.x + elements[7] * v.y + elements[11] * v.z + elements[15] * v.w;
        return out;
    }

    public boolean epsilonEquals(Mat4 other, float epsilon) {
        for (int i = 0; i < elements.length; i++) {
            if (Math.abs(elements[i] - other.elements[i]) > epsilon) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Mat4{" +
                "elements=" + Arrays.toString(elements) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Mat4 mat4 = (Mat4) o;
        return Objects.deepEquals(elements, mat4.elements);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(elements);
    }

}
