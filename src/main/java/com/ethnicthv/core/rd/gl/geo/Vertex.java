package com.ethnicthv.core.rd.gl.geo;

import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;

public class Vertex implements VerticeConvert {
    private float x;
    private float y;
    private float z;

    public Vertex(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vertex(float x, float y) {
        this(x, y, 0);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public FloatBuffer toNative() {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer buffer = stack.mallocFloat(3);
            buffer.put(new float[]{x, y, z}).flip();
            return buffer;
        }
    }
}

