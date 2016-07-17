#ifdef GL_ES
    #define LOWP lowp
    precision highp float;
#else
    #define LOWP
#endif

varying LOWP vec4 v_color;
varying vec2 v_texCoords;
uniform sampler2D u_texture;
uniform float u_resolutionY;
uniform float u_scale;

void main() {
    if (mod(floor(gl_FragCoord.y), 6.0) == 0.0)
        gl_FragColor = vec4(0.0, 0.0, 0.0, 1.0);
    else
        gl_FragColor = v_color * texture2D(u_texture, v_texCoords);
}