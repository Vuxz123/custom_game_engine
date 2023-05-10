package com.ethnicthv.util.gl.error;

import com.ethnicthv.util.gl.GLFWUtil;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class Window {
    private final long windowHandle;
    private int width;
    private int height;

    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;

        // Set up an error callback to print any GLFW errors to the console
        GLFWUtil.setErrorCallBack(System.out);

        // Initialize GLFW and create a window
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);
        windowHandle = GLFW.glfwCreateWindow(width, height, title, 0, 0);

        if (windowHandle == 0) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // Set the window context current
        GLFW.glfwMakeContextCurrent(windowHandle);

        // Enable v-sync
        GLFW.glfwSwapInterval(1);

        // Make the window visible
        GLFW.glfwShowWindow(windowHandle);

        // Create an OpenGL context
        GL.createCapabilities();

        // Set the clear color to black
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public long getWindowHandle() {
        return windowHandle;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void destroy() {
        GLFW.glfwDestroyWindow(windowHandle);
        GLFW.glfwSetErrorCallback(null).free();
    }

    public void update() {
        // Swap the buffers and poll for any events
        GLFW.glfwSwapBuffers(windowHandle);
        GLFW.glfwPollEvents();

        // Clear the framebuffer
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(windowHandle);
    }

    public void setResizable(boolean resizable) {
        GLFW.glfwSetWindowAttrib(windowHandle, GLFW.GLFW_RESIZABLE, resizable ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);
    }

}

