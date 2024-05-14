package upo.graph.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import upo.graph.base.Edge;
import upo.graph.base.Vertex;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class AdjMatrixUndirWeightedTest {

    private AdjMatrixUndirWeighted graphWeighted;

    @BeforeEach
    void setUpWeighted() {
        graphWeighted = new AdjMatrixUndirWeighted();
    }

    @org.junit.jupiter.api.Test
    void addVertexWeighted() {
        Vertex v1 = Vertex.getVertexByLabel("v1");
        Vertex v2 = Vertex.getVertexByLabel("v2");
        Vertex v3 = Vertex.getVertexByLabel("v3");
        Vertex v4 = Vertex.getVertexByLabel("v4");

        assertEquals(0, graphWeighted.addVertex(v1));
        assertEquals(1, graphWeighted.addVertex(v2));
        assertEquals(2, graphWeighted.addVertex(v3));

        assertTrue(graphWeighted.containsVertex(v1));
        assertTrue(graphWeighted.containsVertex(v2));
        assertTrue(graphWeighted.containsVertex(v3));
        assertFalse(graphWeighted.containsVertex(v4));
    }

    @org.junit.jupiter.api.Test
    void addEdgeWeighted() {
        Vertex v1 = Vertex.getVertexByLabel("A");
        Vertex v2 = Vertex.getVertexByLabel("B");
        Vertex v3 = Vertex.getVertexByLabel("C");

        graphWeighted.addVertexWeighted(v1);
        graphWeighted.addVertexWeighted(v2);
        graphWeighted.addVertexWeighted(v3);

        Edge edge1 = Edge.getEdgeByVertexes(v1, v2);
        Edge edge2 = Edge.getEdgeByVertexes(v2, v3);
        Edge edge3 = null;

        graphWeighted.addEdgeWeighted(edge1, 3.1);
        graphWeighted.addEdgeWeighted(edge2, 3.3);

        assertTrue(graphWeighted.containsEdge(edge1));
        assertTrue(graphWeighted.containsEdge(edge2));
        assertThrows(IllegalArgumentException.class, () -> graphWeighted.addEdgeWeighted(edge3, 1.0));
    }

    @org.junit.jupiter.api.Test
    void getEdgeWeight() {
        Vertex v1 = Vertex.getVertexByLabel("A");
        Vertex v2 = Vertex.getVertexByLabel("B");
        Vertex v3 = Vertex.getVertexByLabel("C");
        Vertex v4 = Vertex.getVertexByLabel("D");

        graphWeighted.addVertexWeighted(v1);
        graphWeighted.addVertexWeighted(v2);
        graphWeighted.addVertexWeighted(v3);

        Edge edge1 = Edge.getEdgeByVertexes(v1, v2);
        Edge edge2 = Edge.getEdgeByVertexes(v2, v3);
        Edge edge3 = Edge.getEdgeByVertexes(v3, v1);
        Edge edge4 = Edge.getEdgeByVertexes(v4, v2);

        graphWeighted.addEdgeWeighted(edge1, 3.0);
        graphWeighted.addEdgeWeighted(edge2, 3.3);

        assertEquals(3.0, graphWeighted.getEdgeWeight(edge1));
        assertEquals(3.3, graphWeighted.getEdgeWeight(edge2));
        assertThrows(NoSuchElementException.class, () -> graphWeighted.getEdgeWeight(edge3));
        assertThrows(IllegalArgumentException.class, () -> graphWeighted.getEdgeWeight(edge4));

    }

    @Test
    void setEdgeWeight() {
        Vertex v1 = Vertex.getVertexByLabel("A");
        Vertex v2 = Vertex.getVertexByLabel("B");
        Vertex v3 = Vertex.getVertexByLabel("C");

        int i1 = graphWeighted.addVertexWeighted(v1);
        graphWeighted.addVertexWeighted(v2);
        int i3 = graphWeighted.addVertexWeighted(v3);

        Edge edge1 = Edge.getEdgeByVertexes(v1, v2);
        Edge edge2 = Edge.getEdgeByVertexes(v2, v3);

        graphWeighted.addEdge(edge1);
        graphWeighted.addEdge(edge2);

        assertEquals(0, i1);
        assertEquals(2, i3);

        assertEquals(1.0, graphWeighted.getEdgeWeight(edge1));

        graphWeighted.setEdgeWeight(edge1, 3.0);
        graphWeighted.setEdgeWeight(edge2, 3.3);

        assertEquals(3.0, graphWeighted.getEdgeWeight(edge1));
        assertEquals(3.3, graphWeighted.getEdgeWeight(edge2));

    }

    @Test
    void getBellmanFordShortestPaths() {
        Vertex v1 = Vertex.getVertexByLabel("A");
        assertThrows(UnsupportedOperationException.class, () ->  graphWeighted.getBellmanFordShortestPaths(v1));
    }

    @Test
    void getDijkstraShortestPaths() {
        Vertex v1 = Vertex.getVertexByLabel("A");
        assertThrows(UnsupportedOperationException.class, () ->  graphWeighted.getDijkstraShortestPaths(v1));
    }

    @Test
    void getPrimMST() {
        Vertex v1 = Vertex.getVertexByLabel("A");
        assertThrows(UnsupportedOperationException.class, () ->  graphWeighted.getPrimMST(v1));
    }

    @Test
    void getKruskalMST() {
        assertThrows(UnsupportedOperationException.class, () ->  graphWeighted.getKruskalMST());
    }

    @Test
    void getFloydWarshallShortestPaths() {
        assertThrows(UnsupportedOperationException.class, () ->  graphWeighted.getFloydWarshallShortestPaths());
    }
}