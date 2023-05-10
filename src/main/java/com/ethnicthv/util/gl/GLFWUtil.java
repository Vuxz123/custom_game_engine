package com.ethnicthv.util.gl;

import static org.lwjgl.glfw.GLFW.*;

import com.ethnicthv.util.gl.error.GLFWError;
import com.ethnicthv.util.gl.error.Window;
import org.lwjgl.glfw.GLFWErrorCallback;

import java.io.PrintStream;

public class GLFWUtil {
    static RenderThread thread;

    public static void runRenderThread(Window window) {
        thread = new RenderThread(window);
        thread.start();
    }

    public static void setErrorCallBack(PrintStream stream) {
        GLFWErrorCallback.createPrint(stream).set();
    }

    public static void initialize() throws GLFWError {
        if(!glfwInit()) {
            throw new GLFWError();
        }
    }

    public static void cleanup() {
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public static boolean assertRenderThread() {
        return Thread.currentThread() == thread;
    }

}
