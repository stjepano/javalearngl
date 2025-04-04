package gtech.math;

public interface ImmutableFloat3 {
    float x();
    float y();
    float z();

    /** Convert to 3 component vector. Creates a copy. */
    default Vec3 toVec3() {
        return toVec3(new Vec3());
    }

    /** Convert to 3 component vector and store its values in out. Return reference to out. */
    default Vec3 toVec3(Vec3 out) {
        out.x = x();
        out.y = y();
        out.z = z();
        return out;
    }

    /** Create ImmutableFloat3 from Vec3. */
    static ImmutableFloat3 of(Vec3 vec) {
        return new ImmutableFloat3() {
            @Override
            public float x() {
                return vec.x;
            }

            @Override
            public float y() {
                return vec.y;
            }

            @Override
            public float z() {
                return vec.z;
            }
        };
    }
}
