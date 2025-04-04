package learngl;

import gtech.data.PixelSurface;
import gtech.math.IntVec2;
import gtech.math.Quad;
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

import static org.lwjgl.opengl.GL11C.GL_COLOR;
import static org.lwjgl.opengl.GL30C.glClearBufferfv;

public class Main {

    private static final Logger L = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try (LwjglPlatform platform = new LwjglPlatform()) {

            WindowProperties windowProperties = WindowProperties.builder()
                    .title("Learn OpenGL - textures")
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
                window.getFramebufferSize(framebufferSize);
                gl.viewport(0, 0, framebufferSize.x, framebufferSize.y);

                Keyboard keyboard = window.getKeyboard();
                Key escape = keyboard.getKey(KeyCode.ESCAPE);

                FpsPrinter fpsPrinter = new FpsPrinter(1.0, false);

                try (CloseableList<Texture> textures = new CloseableList<>();
                     DemoRenderer demoRenderer = new DemoRenderer(gl)) {

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

                    demoRenderer.setTextureSampler(defaultTextureSampler);

                    Quad quad = Quad.ofCenterAndSize(0, 0, 1, 1);
                    final float rotationSpeed = (float) Math.toRadians(90.0);

                    StopWatch stopWatch = new StopWatch();
                    double deltaT = 0;
                    while (!window.isCloseRequested()) {
                        platform.pollEvents();

                        if (escape.wasJustPressed()) {
                            window.requestClose();
                        }

                        glClearBufferfv(GL_COLOR, 0, clearColor);

                        demoRenderer.draw(quad, containerTexture, faceTexture);

                        window.swapBuffers();

                        deltaT = stopWatch.elapsedTime();
                        stopWatch.reset();

                        quad.rotate((float) (rotationSpeed * deltaT));

                        fpsPrinter.update(deltaT);
                    }
                }


            }

        } catch (Exception e) {
            L.error("An error occurred", e);
        }
    }


}
