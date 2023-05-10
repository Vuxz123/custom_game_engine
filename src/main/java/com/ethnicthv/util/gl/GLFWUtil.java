package com.ethnicthv.util.gl;

import static org.lwjgl.glfw.GLFW.*;

import com.ethnicthv.util.gl.error.GLFWError;
import com.ethnicthv.util.gl.error.Window;
import org.lwjgl.glfw.GLFWErrorCallback;

import java.io.PrintStream;

public class GLFWUtil {
    static Thread renderThread;

    public static Window window;

    public static void setErrorCallBack(PrintStream stream) {
        GLFWErrorCallback.createPrint(stream).set();
    }

    public static void initialize() throws GLFWError {
        renderThread = Thread.currentThread();
        if(!glfwInit()) {
            throw new GLFWError();
        }
    }

    public static void cleanup() {
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public static boolean assertRenderThread() {
        return Thread.currentThread() == renderThread;
    }

    public static void createWindow() {
        window = new Window(100,100,"Test");
    }

}
