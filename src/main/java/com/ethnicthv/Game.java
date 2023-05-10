package com.ethnicthv;

import com.ethnicthv.util.gl.GLFWUtil;
import com.ethnicthv.util.gl.error.GLFWError;

import static org.lwjgl.glfw.GLFW.glfwTerminate;

public class Game {
    public static Game INSTANCE = new Game();

    public void run() {
        init();

        loop();

        close();
    }

    private void init() {
        try {
            GLFWUtil.initialize();
        } catch (GLFWError e) {
            throw new RuntimeException(e);
        }
        GLFWUtil.createWindow();

    }

    private void loop() {
        while (!GLFWUtil.window.shouldClose()) {
            GLFWUtil.window.update();
        }
    }

    private void close() {
        GLFWUtil.window.destroy();
        glfwTerminate();
    }

}
