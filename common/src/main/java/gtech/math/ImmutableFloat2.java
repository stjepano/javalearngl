package gtech.math;

/**
 * This type is used as return value when you want to encapsulate the object.
 */
public interface ImmutableFloat2 {
    /** The x component */
    float x();
    /** The y component */
    float y();

    /** Convert to 2 component vector. Creates a copy. */
    default Vec2 toVec2() {
        return toVec2(new Vec2());
    }

    /** Convert to 2 component vector and store its values in out. Return reference to out. */
    default Vec2 toVec2(Vec2 out) {
        out.x = x();
        out.y = y();
        return out;
    }

    /** Create ImmutableFloat2 from Vec2. */
    static ImmutableFloat2 of(Vec2 vec2) {
        return new ImmutableFloat2() {
            @Override
            public float x() {
                return vec2.x;
            }
            @Override
            public float y() {
                return vec2.y;
            }
        };
    }
}
