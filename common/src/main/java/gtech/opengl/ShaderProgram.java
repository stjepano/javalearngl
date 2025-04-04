package gtech.opengl;

import gtech.math.Mat4;
import gtech.math.Vec2;
import gtech.math.Vec3;
import gtech.math.Vec4;

import java.util.function.Supplier;

import static org.lwjgl.opengl.GL20C.*;

public abstract class ShaderProgram implements AutoCloseable {

    private final GLMaster glMaster;
    protected int id;

    public ShaderProgram(GLMaster glMaster, int id) {
        this.glMaster = glMaster;
        this.id = id;
    }

    public int getUniformLocationOrThrow(String name, Supplier<RuntimeException> exceptionSupplier) {
        int location = glGetUniformLocation(id, name);
        if (location < 0) {
            throw exceptionSupplier.get();
        }
        return location;
    }

    public int getUniformLocation(String name) {
        return glGetUniformLocation(id, name);
    }

    public void setUniform(int location, float x, float y) {
        glUniform2f(location, x, y);
    }

    public void setUniform(int location, Vec2 vec) {
        glUniform2f(location, vec.x, vec.y);
    }

    public void setUniform(int location, float x, float y, float z) {
        glUniform3f(location, x, y, z);
    }

    public void setUniform(int location, Vec3 vec) {
        glUniform3f(location, vec.x, vec.y, vec.z);
    }

    public void setUniform(int location, float x, float y, float z, float w) {
        glUniform4f(location, x, y, z, w);
    }

    public void setUniform(int location, Vec4 vec) {
        glUniform4f(location, vec.x, vec.y, vec.z, vec.w);
    }

    public void setUniform(int location, TextureUnit textureUnit) {
        glUniform1i(location, textureUnit.ordinal());
    }

    public void setUniform(int location, Mat4 matrix) {
        glUniformMatrix4fv(location, false, matrix.elements);
    }

    @Override
    public void close() {
        if (this.id != 0) {
            glDeleteProgram(this.id);
            this.id = 0;
        }
    }
}
