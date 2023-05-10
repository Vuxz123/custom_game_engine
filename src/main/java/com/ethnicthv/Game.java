package com.ethnicthv;

import com.ethnicthv.util.gl.RenderSystem;
import com.ethnicthv.util.gl.error.GLFWError;

public class Game {
    public static Game INSTANCE = new Game();

    public void run() {
        init();

        loop();

        close();
    }

    private void init() {
        try {
            RenderSystem.initialize();
        } catch (GLFWError e) {
            throw new RuntimeException(e);
        }
        RenderSystem.createWindow(1000,900, "Test");
    }

    private void loop() {
        while (!RenderSystem.window.shouldClose()) {
            RenderSystem.window.update();
        }
    }

    private void close() {
        RenderSystem.destroy();
    }

}
