package gtech.math;

import java.util.Objects;

public final class Color4 {
    public float r, g, b, a;

    public Color4() {
        this(0, 0, 0, 1);
    }

    public Color4(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public float[] toArray() {
        return toArray(new float[4]);
    }

    public float[] toArray(float[] array) {
        return toArray(array, 0);
    }

    public float[] toArray(float[] array, int offset) {
        array[offset] = r;
        array[offset + 1] = g;
        array[offset + 2] = b;
        array[offset + 3] = a;
        return array;
    }

    @Override
    public String toString() {
        return "Color4{" +
                "r=" + r +
                ", g=" + g +
                ", b=" + b +
                ", a=" + a +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Color4 color4 = (Color4) o;
        return Float.compare(r, color4.r) == 0 && Float.compare(g, color4.g) == 0 && Float.compare(b, color4.b) == 0 && Float.compare(a, color4.a) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, g, b, a);
    }

    public static Color4 white() {
        return new Color4(1, 1, 1, 1);
    }

    public static Color4 black() {
        return new Color4(0, 0, 0, 1);
    }

    public static Color4 red() {
        return new Color4(1, 0, 0, 1);
    }

    public static Color4 green() {
        return new Color4(0, 1, 0, 1);
    }

    public static Color4 blue() {
        return new Color4(0, 0, 1, 1);
    }

}
