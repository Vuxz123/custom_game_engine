package com.ethnicthv;

import com.ethnicthv.util.gl.GLFWUtil;
import com.ethnicthv.util.gl.error.GLFWError;
import com.ethnicthv.util.gl.error.Window;

import static org.lwjgl.glfw.GLFW.*;

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
        GLFWUtil.runRenderThread(new Window(100, 100, "abc"));
    }

    private void loop() {

    }

    private void close() {
        glfwTerminate();
    }

}
