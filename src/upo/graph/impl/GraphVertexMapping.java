package upo.graph.impl;

import upo.graph.base.Vertex;
import java.util.ArrayList;
import java.util.List;

public abstract class GraphVertexMapping {

    protected final List<Vertex> vertexList;

    public GraphVertexMapping() {
        vertexList = new ArrayList<>();
    }

    protected int getIndexOf(Vertex vertex) {
        if(!vertexList.contains(vertex)) throw new IllegalArgumentException();
        return vertexList.indexOf(vertex);
    }

    protected Vertex getVertex(int index) {
        if(index < 0 || index >= vertexList.size()) throw new IndexOutOfBoundsException(" ");
        return vertexList.get(index);
    }

    protected int addVertexIndex(Vertex vertex) {
        if(vertexList.contains(vertex)) throw new UnsupportedOperationException();
        vertexList.add(vertex);
        return vertexList.size();
    }

    protected int removeVertexIndex(Vertex vertex) {
        if(!vertexList.contains(vertex)) throw new IllegalArgumentException();
        int index = vertexList.indexOf(vertex);
        vertexList.remove(index);
        return index;
    }
}
