package gtech.math;

public final class IntVec2 {
    public int x, y;
    
    public IntVec2() {
        this(0, 0);
    }
    
    public IntVec2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public IntVec2 addSelf(IntVec2 vec) {
        x += vec.x;
        y += vec.y;
        return this;
    }

    public IntVec2 add(IntVec2 vec) {
        return add(new IntVec2(), vec);
    }

    public IntVec2 add(IntVec2 out, IntVec2 vec) {
        out.x = x + vec.x;
        out.y = y + vec.y;
        return out;
    }

    public IntVec2 subSelf(IntVec2 vec) {
        x -= vec.x;
        y -= vec.y;
        return this;
    }

    public IntVec2 sub(IntVec2 vec) {
        return sub(new IntVec2(), vec);
    }

    public IntVec2 sub(IntVec2 out, IntVec2 vec) {
        out.x = x - vec.x;
        out.y = y - vec.y;
        return out;
    }

    public IntVec2 mulsSelf(int scalar) {
        x *= scalar;
        y *= scalar;
        return this;
    }

    public IntVec2 muls(int scalar) {
        return muls(new IntVec2(), scalar);
    }

    public IntVec2 muls(IntVec2 out, int scalar) {
        out.x = x * scalar;
        out.y = y * scalar;
        return out;
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
