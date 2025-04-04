#version 330 core
out vec4 oFragColor;

in vec2 vTexCoord;
in vec3 vVertexColor;

uniform sampler2D uTexture1;
uniform sampler2D uTexture2;

void main() {
    oFragColor = mix(texture(uTexture1, vTexCoord), texture(uTexture2, vTexCoord), 0.2); //* vec4(vVertexColor * 0.2, 1.0);
}