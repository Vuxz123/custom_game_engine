package com.ethnicthv.util.gl;

import com.ethnicthv.util.gl.error.GLFWError;
import com.ethnicthv.util.gl.error.Window;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;

import java.io.PrintStream;

import static org.lwjgl.glfw.GLFW.*;

public class RenderSystem {
    static Thread renderThread;

    public static Window window;

    public static void setErrorCallBack(PrintStream stream) {
        GLFWErrorCallback.createPrint(stream).set();
    }

    public static void initialize() throws GLFWError {
        renderThread = Thread.currentThread();
        setErrorCallBack(System.out);
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }
    }

    public static void assertRenderThread() {
        if (renderThread == null) throw new AssertionError("GLFW not init properly");
        if (Thread.currentThread() != renderThread) throw new AssertionError("Call not on the RenderThread!");
    }

    public static void createWindow(int width, int height, String tilte) {
        window = new Window(width, height, tilte);
    }

    public static void destroy() {
        window.destroy();
        GLFW.glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

}
