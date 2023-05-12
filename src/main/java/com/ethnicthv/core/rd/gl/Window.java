package com.ethnicthv.core.rd.gl;

import org.joml.Vector2i;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

public class Window {
    private final long windowHandle;
    private boolean isVsync = false;

    public Window(int width, int height, String title) {

        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);
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

    public boolean isVsync() {
        return isVsync;
    }

    public void toggleVsync() {
        RenderSystem.assertRenderThread();
        isVsync = !isVsync;
        GLFW.glfwSwapInterval(isVsync ? 1 : 0);
    }

    public long getWindowHandle() {
        return windowHandle;
    }

    public Vector2i getSize() {
        RenderSystem.assertRenderThread();
        int width, height;
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer int1 = stack.callocInt(1);
            IntBuffer int2 = stack.callocInt(1);
            GLFW.glfwGetWindowSize(windowHandle, int1, int2);
            width = int1.get(0);
            height = int2.get(0);
        }
        return new Vector2i(width, height);
    }

    public void destroy() {
        RenderSystem.assertRenderThread();
        GLFW.glfwDestroyWindow(windowHandle);
    }

    public void update() {
        RenderSystem.assertRenderThread();
        // Swap the buffers and poll for any events
        GLFW.glfwSwapBuffers(windowHandle);
        GLFW.glfwPollEvents();

        // Clear the framebuffer
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    public boolean shouldClose() {
        RenderSystem.assertRenderThread();
        return GLFW.glfwWindowShouldClose(windowHandle);
    }

    public void setResizable(boolean resizable) {
        GLFW.glfwSetWindowAttrib(windowHandle, GLFW.GLFW_RESIZABLE, resizable ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);
    }

}

