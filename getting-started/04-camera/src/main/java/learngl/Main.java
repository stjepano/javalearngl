package learngl;

import gtech.data.PixelSurface;
import gtech.math.*;
import gtech.opengl.*;
import gtech.opengl.opengl3.GL3Master;
import gtech.platform.*;
import gtech.util.CloseableList;
import gtech.util.FpsPrinter;
import gtech.util.StopWatch;
import org.lwjgl.opengl.GL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class Main {

    private static final Logger L = LoggerFactory.getLogger(Main.class);

    private static final float ROTATION_SPEED = (float) Math.toRadians(45.0);
    private static final float FOV_DELTA = (float) Math.toRadians(5.0);
    private static final float MIN_FOV = (float) Math.toRadians(5.0);

    public static void main(String[] args) {
        try (LwjglPlatform platform = new LwjglPlatform()) {

            WindowProperties windowProperties = WindowProperties.builder()
                    .title("Learn OpenGL - camera")
                    .size(1920, 1080)
                    .vsync(false)
                    .visible(false)
                    .resizable(true)
                    .glVersion(3, 3)
                    .build();

            final float[] clearColor = {0.2f, 0.3f, 0.3f, 1.0f};
            try (Window window = platform.createWindow(windowProperties)) {
                window.makeContextCurrent();
                window.show();
                GL.createCapabilities();

                GLMaster gl = new GL3Master();
                IntVec2 framebufferSize = new IntVec2();

                Keyboard keyboard = window.getKeyboard();
                Mouse mouse = window.getMouse();
                Key escape = keyboard.getKey(KeyCode.ESCAPE);
                Button leftButton = mouse.getButton(ButtonCode.LEFT);
                Button rightButton = mouse.getButton(ButtonCode.RIGHT);

                FpsPrinter fpsPrinter = new FpsPrinter(1.0, false);

                DemoCamera.InputMapping inputMapping = new DemoCamera.InputMapping(
                        keyboard.getKey(KeyCode.W), keyboard.getKey(KeyCode.A), keyboard.getKey(KeyCode.D),
                        keyboard.getKey(KeyCode.S), keyboard.getKey(KeyCode.Q), keyboard.getKey(KeyCode.E),
                        mouse.getButton(ButtonCode.RIGHT)
                );
                DemoCamera camera = new DemoCamera(inputMapping);
                camera.setPosition(Vec3.of(0, 0, 10));

                DemoMesh cubeMesh = DemoMesh.ofCube(1.0f, 1.0f, 1.0f);
                try (CloseableList<Texture> textures = new CloseableList<>();
                     DemoMeshRenderer demoMeshRenderer = new DemoMeshRenderer(gl, cubeMesh)) {

                    Texture containerTexture, faceTexture;
                    try (PixelSurface pixelSurface = STBPixelSurface.load(new File("assets/textures/container.png"), true)) {
                        containerTexture = gl.createTexture(pixelSurface, TextureFormat.RGBA8, true);
                        textures.add(containerTexture);
                    }
                    try (PixelSurface pixelSurface = STBPixelSurface.load(new File("assets/textures/awesomeface.png"), true)) {
                        faceTexture = gl.createTexture(pixelSurface, TextureFormat.RGBA8, true);
                        textures.add(faceTexture);
                    }

                    TextureSampler defaultTextureSampler = TextureSampler.builder()
                            .wrap(TextureWrap.REPEAT, TextureWrap.REPEAT)
                            .filter(TextureFilter.LINEAR, TextureFilter.LINEAR)
                            .mipmapFilter(TextureFilter.LINEAR)
                            .build();

                    demoMeshRenderer.setTextureSampler(defaultTextureSampler);

                    StopWatch stopWatch = new StopWatch();
                    double deltaT = 0;

                    Mat4 projectionMatrix = Mat4.identity();

                    Mat4 modelMatrix = Mat4.identity();
                    float fov = (float) Math.toRadians(45.0);

                    final Vec3[] cubePositions = new Vec3[] {
                            Vec3.of(0.0f, 0.0f, 0.0f),
                            Vec3.of(2.0f, 5.0f, -15.0f),
                            Vec3.of(-1.5f, -2.2f, -2.5f),
                            Vec3.of(-3.8f, -2.0f, -12.3f),
                            Vec3.of( 2.4f, -0.4f, -3.5f),
                            Vec3.of(-1.7f,  3.0f, -7.5f),
                            Vec3.of( 1.3f, -2.0f, -2.5f),
                            Vec3.of( 1.5f,  2.0f, -2.5f),
                            Vec3.of( 1.5f,  0.2f, -1.5f),
                            Vec3.of(-1.3f,  1.0f, -1.5f)
                    };

                    final Vec3[] cubeAxes = new Vec3[] {
                            Vec3.of(1.0f, 0.0f, 0.0f).normalizeSelf(),
                            Vec3.of(0.0f, 1.0f, 0.0f).normalizeSelf(),
                            Vec3.of(0.0f, 0.0f, 1.0f).normalizeSelf(),
                            Vec3.of(2, 3, 4).normalizeSelf(),
                            Vec3.of(1, -3, -2).normalizeSelf(),
                            Vec3.of(2.3f, -4f, 1.0f).normalizeSelf(),
                            Vec3.of(7.0f, 1.0f, 0.0f).normalizeSelf(),
                            Vec3.of(0.0f, -5.0f, 1.0f).normalizeSelf(),
                            Vec3.of(2, 5, 4).normalizeSelf(),
                            Vec3.of(1, -5, -2).normalizeSelf(),
                    };

                    final Quaternion[] cubeRotations = new Quaternion[cubePositions.length];
                    for (int i = 0; i < cubeRotations.length; i++) {
                        cubeRotations[i] = Quaternion.ofAngleAxis(0, cubeAxes[i]);
                    }

                    while (!window.isCloseRequested()) {
                        platform.pollEvents();

                        if (mouse.getScrollDelta() != 0) {
                            fov += FOV_DELTA * -mouse.getScrollDelta();
                        }

                        if (leftButton.wasClicked()) {
                            System.err.println("Left button clicked, position: " + mouse.getPosition());
                        }
                        if (leftButton.wasDoubleClicked()) {
                            System.err.println("Left button double clicked, position: " + mouse.getPosition());
                        }
                        if (leftButton.wasTripleClicked()) {
                            System.err.println("Left button triple clicked, position: " + mouse.getPosition());
                        }

                        if (rightButton.wasClicked()) {
                            System.err.println("Right button clicked, position: " + mouse.getPosition());
                        }

                        if (fov < MIN_FOV) {
                            fov = MIN_FOV;
                        }

                        window.getFramebufferSize(framebufferSize);
                        gl.viewport(0, 0, framebufferSize.x, framebufferSize.y);

                        float aspectRatio = (float) framebufferSize.x / (float) framebufferSize.y;
                        projectionMatrix.setPerspective(fov, aspectRatio, 0.1f, 100.0f);
                        demoMeshRenderer.setProjectionMatrix(projectionMatrix);

                        if (escape.wasJustPressed()) {
                            window.requestClose();
                        }

                        gl.clearColorBuffer(clearColor);
                        gl.clearDepthBuffer(1.0f);

                        camera.update((float)deltaT);

                        for (int i = 0; i < cubePositions.length; i++) {
                            Vec3 cubePosition = cubePositions[i];
                            Quaternion cubeRotation = cubeRotations[i];
                            cubeRotation.mulSelf(Quaternion.ofAngleAxis(ROTATION_SPEED * (float)deltaT, cubeAxes[i]));

                            modelMatrix.setTranslation(cubePosition);
                            modelMatrix.rotateSelf(cubeRotation);

                            demoMeshRenderer.draw(camera.getViewMatrix(), modelMatrix, containerTexture, faceTexture);
                        }

                        window.swapBuffers();

                        deltaT = stopWatch.elapsedTime();
                        stopWatch.reset();

                        fpsPrinter.update(deltaT);
                    }
                }


            }

        } catch (Exception e) {
            L.error("An error occurred", e);
        }
    }


}
