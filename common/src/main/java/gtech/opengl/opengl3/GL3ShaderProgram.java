package gtech.opengl.opengl3;

import gtech.opengl.GLMaster;
import gtech.opengl.ShaderProgram;

class GL3ShaderProgram extends ShaderProgram {
    public GL3ShaderProgram(GLMaster glMaster, int id) {
        super(glMaster, id);
    }

    int getProgramId() {
        return this.id;
    }
}
