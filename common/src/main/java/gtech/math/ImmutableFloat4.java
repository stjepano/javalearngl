package gtech.math;

public interface ImmutableFloat4 {
    float x();
    float y();
    float z();
    float w();

    default Vec4 toVec4() {
        return toVec4(new Vec4());
    }

    default Vec4 toVec4(Vec4 out) {
        out.x = x();
        out.y = y();
        out.z = z();
        out.w = w();
        return out;
    }

    static ImmutableFloat4 of(Vec4 vec) {
        return new ImmutableFloat4() {
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
            @Override
            public float w() {
                return vec.w;
            }
        };
    }
}
