package com.ethnicthv.core.rd.gl.geo;

import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

public class Quad implements VerticeConvert {
    private final Vertex[] vertices;

    public Quad(Vertex v1, Vertex v2, Vertex v3, Vertex v4) {
        this.vertices = new Vertex[]{v1, v2, v3, v4};
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public FloatBuffer toNative() {
        FloatBuffer buffer = MemoryUtil.memAllocFloat(vertices.length * 3 * 2);

        for (Vertex vertex : vertices) {
            buffer.put(vertex.toNative());
        }

        buffer.flip();
        return buffer;
    }
}
