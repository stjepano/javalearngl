package gtech.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Mat4Test {

    @Test
    public void setZero() {
        Mat4 mat = new Mat4();
        mat.setZero();

        final Mat4 expected = new Mat4(new float[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        assertEquals(expected, mat);
    }

    @Test
    public void setIdentity() {
        Mat4 mat = new Mat4();
        mat.setIdentity();

        final Mat4 expected = new Mat4(new float[] {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1});
        assertEquals(expected, mat);
    }

    @Test
    public void transpose() {
        Mat4 mat = new Mat4(new float[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16});
        Mat4 expected = new Mat4(new float[] {1, 5, 9, 13, 2, 6, 10, 14, 3, 7, 11, 15, 4, 8, 12, 16});

        Mat4 transposed = mat.transpose();
        assertEquals(expected, transposed);

        Mat4 matCopy = new Mat4(mat);
        matCopy.transposeSelf();
        assertEquals(expected, matCopy);
    }

    @Test
    public void muls() {
        Mat4 mat = new Mat4(new float[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16});
        Mat4 expected = new Mat4(new float[] {2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32});

        Mat4 result = mat.muls(2);
        assertEquals(expected, result);

        Mat4 matCopy = new Mat4(mat);
        matCopy.mulsSelf(2);
        assertEquals(expected, matCopy);
    }

    @Test
    public void mul() {
        Mat4 a = new Mat4(new float[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16});
        Mat4 b = a.transpose();

        Mat4 expected = new Mat4(new float[] {276, 304, 332, 360, 304, 336, 368, 400, 332, 368, 404, 440, 360, 400, 440, 480});

        Mat4 result = a.mul(b);
        assertEquals(expected, result);

        Mat4 aCopy = new Mat4(a);
        Mat4 bCopy = new Mat4(b);
        aCopy.mulSelf(bCopy);
        assertEquals(expected, aCopy);
    }

    @Test
    public void determinant() {
        Mat4 mat = new Mat4(new float[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16});
        assertEquals(0, mat.determinant());

        Mat4 mat2 = new Mat4(new float[] {1, 0, 3, 4, 5, 6, 7, 8, 9, 10, 3, 12, 13, 14, 15, 16});
        assertEquals(384, mat2.determinant());
    }

    @Test
    public void inverse() {
        Mat4 mat = new Mat4(new float[] {1, 0, 3, 4, 5, 6, 7, 8, 9, 10, 3, 12, 13, 14, 15, 16});

        Mat4 inverse = mat.inverse();
        Mat4 mul = mat.mul(inverse);
        Mat4 identity = Mat4.identity();
        assertTrue(mul.epsilonEquals(identity, 1e-6f));

        Mat4 matCopy = new Mat4(mat);
        matCopy.inverseSelf();
        assertEquals(inverse, matCopy);
    }

    @Test
    public void mulVector() {
        Mat4 mat = new Mat4(new float[] {1, 0, 3, 4, 5, 6, 7, 8, 9, 10, 3, 12, 13, 14, 15, 16});
        Vec4 vec = new Vec4(1, 2, 3, 4);

        Vec4 res = mat.mul(vec);
        Vec4 expected = new Vec4(90, 98, 86, 120);
        assertEquals(expected, res);
    }

}