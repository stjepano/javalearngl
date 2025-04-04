package learngl;

import java.util.*;

/**
 * A simple mesh system with positions and texture coordinates.
 *
 * Vertex format: px, py, pz, u, v
 */
public final class DemoMesh {
    private final float[] vertices;
    private final int[] indices;

    private DemoMesh(float[] vertices, int[] indices) {
        this.vertices = vertices;
        this.indices = indices;
    }

    public static DemoMesh ofCube(float width, float height, float depth) {
        float x = width / 2.0f;
        float y = height / 2.0f;
        float z = depth / 2.0f;
        final float[] positions = new float[] {
                -x,  y,  z,  // A (0)
                -x, -y,  z,  // B (1)
                 x, -y,  z,  // C (2)
                 x,  y,  z,  // D (3)
                 x, -y, -z,  // E (4)
                 x,  y, -z,  // F (5)
                -x,  y, -z,  // G (6)
                -x, -y, -z   // H (7)
        };
        final float[] texCoords = new float[] {
                0.0f, 0.0f, // bottom-left
                1.0f, 0.0f, // bottom-right
                1.0f, 1.0f, // top-right
                0.0f, 1.0f  // top-left
        };
        final int[] vertices = new int[] {
                0,3, 1,0, 2,1, 3,2, // front
                3,3, 2,0, 4,1, 5,2, // right
                5,3, 4,0, 7,1, 6,2, // back
                6,3, 7,0, 1,1, 0,2, // left
                6,3, 0,0, 3,1, 5,2, // top
                1,3, 7,0, 4,1, 2,2  // bottom
        };

        return buildTriangleMeshFromQuads(positions, texCoords, vertices);
    }

    public float[] getVertices() {
        return vertices;
    }

    public int[] getIndices() {
        return indices;
    }


    private static class IntPair {
        public int first;
        public int second;

        public IntPair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            IntPair intPair = (IntPair) o;
            return first == intPair.first && second == intPair.second;
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }

    private static DemoMesh buildTriangleMeshFromQuads(float[] positions, float[] texCoords, int[] vertices) {
        int currentIndex = 0;
        final Map<IntPair, Integer> uniqueVertices = new HashMap<>();
        final ArrayList<Float> vertexData = new ArrayList<>(positions.length * texCoords.length * 5);
        final ArrayList<Integer> indexList = new ArrayList<>(positions.length * texCoords.length);
        for (int i = 0; i < vertices.length; i += 2) {
            IntPair key = new IntPair(vertices[i], vertices[i + 1]);
            Integer index = uniqueVertices.get(key);
            if (index == null) {
                index = currentIndex;
                currentIndex++;
                uniqueVertices.put(key, index);

                int positionArrayIndex = vertices[i];
                int texCoordArrayIndex = vertices[i + 1];

                vertexData.add(positions[positionArrayIndex * 3]);
                vertexData.add(positions[positionArrayIndex * 3 + 1]);
                vertexData.add(positions[positionArrayIndex * 3 + 2]);
                vertexData.add(texCoords[texCoordArrayIndex * 2]);
                vertexData.add(texCoords[texCoordArrayIndex * 2 + 1]);
            }
            indexList.add(index);
        }

        final float[] verticesArray = new float[vertexData.size()];
        for (int i = 0; i < vertexData.size(); i++) {
            verticesArray[i] = vertexData.get(i);
        }

        int facesCount = vertices.length / (2 * 4);

        final int[] indicesArray = new int[facesCount * 6];
        for (int i = 0; i < facesCount; i++) {
            indicesArray[i * 6] = indexList.get(i * 4);
            indicesArray[i * 6 + 1] = indexList.get(i * 4 + 1);
            indicesArray[i * 6 + 2] = indexList.get(i * 4 + 2);
            indicesArray[i * 6 + 3] = indexList.get(i * 4);
            indicesArray[i * 6 + 4] = indexList.get(i * 4 + 2);
            indicesArray[i * 6 + 5] = indexList.get(i * 4 + 3);
        }

        return new DemoMesh(verticesArray, indicesArray);
    }
}
