package gtech.math;

public final class Complex {
    public float real, imaginary;

    public Complex() {
        this(0, 0);
    }

    public Complex(float real, float imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex(Complex other) {
        this(other.real, other.imaginary);
    }

    public static Complex ofAngle(float angleRadians) {
        Complex out = new Complex();
        out.real = (float) Math.cos(angleRadians);
        out.imaginary = (float) Math.sin(angleRadians);
        return out;
    }

    public Complex conjugateSelf() {
        imaginary = -imaginary;
        return this;
    }

    public Complex conjugate() {
        return conjugate(new Complex());
    }

    public Complex conjugate(Complex out) {
        out.real = real;
        out.imaginary = -imaginary;
        return out;
    }

    public Complex inverseSelf() {
        float lengthSquared = lengthSqr();

        float newReal = real / lengthSquared;
        float newImaginary = -imaginary / lengthSquared;

        real = newReal;
        imaginary = newImaginary;

        return this;
    }

    public Vec2 rotate(Vec2 v) {
        return rotate(new Vec2(), v);
    }

    public Vec2 rotate(Vec2 out, Vec2 v) {
        out.x = v.x * this.real - v.y * this.imaginary;
        out.y = v.x * this.imaginary + v.y * this.real;
        return out;
    }

    public Complex inverse() {
        return inverse(new Complex());
    }

    public Complex inverse(Complex out) {
        float lengthSquared = lengthSqr();

        out.real = real / lengthSquared;
        out.imaginary = -imaginary / lengthSquared;

        return out;
    }

    public float length() {
        return (float) Math.sqrt(lengthSqr());
    }

    public float lengthSqr() {
        return real * real + imaginary * imaginary;
    }

    public Complex mulSelf(Complex other) {
        float newReal = real * other.real - imaginary * other.imaginary;
        float newImaginary = real * other.imaginary + imaginary * other.real;

        real = newReal;
        imaginary = newImaginary;

        return this;
    }

    public Complex mul(Complex other) {
        return mul(new Complex(), other);
    }

    public Complex mul(Complex out, Complex other) {
        float newReal = real * other.real - imaginary * other.imaginary;
        float newImaginary = real * other.imaginary + imaginary * other.real;

        out.real = newReal;
        out.imaginary = newImaginary;

        return out;
    }

}
