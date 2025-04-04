#version 330 core

layout (location = 0) in vec2 aPos;
layout (location = 1) in vec2 aTexCoord;

out vec2 vTexCoord;

uniform mat4 uProjectionMatrix;
uniform mat4 uModelMatrix;

void main() {
    gl_Position = uProjectionMatrix * uModelMatrix * vec4(aPos, 0.0, 1.0);
    vTexCoord = aTexCoord;
}