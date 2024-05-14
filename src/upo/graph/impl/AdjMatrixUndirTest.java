package upo.graph.impl;
import org.junit.jupiter.api.BeforeEach;
import upo.graph.base.Edge;
import upo.graph.base.VisitForest;
import upo.graph.base.Vertex;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AdjMatrixUndirTest {

    private AdjMatrixUndir graph;

    @BeforeEach
    void setUp() {
        graph = new AdjMatrixUndir();
    }

    @org.junit.jupiter.api.Test
    void addVertex() {
        Vertex v1 = Vertex.getVertexByLabel("v1");
        Vertex v2 = Vertex.getVertexByLabel("v2");
        Vertex v3 = Vertex.getVertexByLabel("v3");
        Vertex v4 = Vertex.getVertexByLabel("v4");

        assertEquals(0, graph.addVertex(v1));
        assertEquals(1, graph.addVertex(v2));
        assertEquals(2, graph.addVertex(v3));

        assertTrue(graph.containsVertex(v1));
        assertTrue(graph.containsVertex(v2));
        assertTrue(graph.containsVertex(v3));
        assertFalse(graph.containsVertex(v4));
    }

    @org.junit.jupiter.api.Test
    void getVertices() {
        Vertex v1 = Vertex.getVertexByLabel("A");
        Vertex v2 = Vertex.getVertexByLabel("B");
        Vertex v3 = Vertex.getVertexByLabel("C");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);

        Set<Vertex> vertices = graph.getVertices();

        assertEquals(3, vertices.size());
        assertTrue(vertices.contains(v1));
        assertTrue(vertices.contains(v2));
        assertTrue(vertices.contains(v3));

    }

    @org.junit.jupiter.api.Test
    void getEdges() {
        Vertex v1 = Vertex.getVertexByLabel("A");
        Vertex v2 = Vertex.getVertexByLabel("B");
        Vertex v3 = Vertex.getVertexByLabel("C");
        Vertex v4 = Vertex.getVertexByLabel("D");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);

        graph.addEdge(Edge.getEdgeByVertexes(v1, v2));
        graph.addEdge(Edge.getEdgeByVertexes(v2, v3));
        graph.addEdge(Edge.getEdgeByVertexes(v3, v1));
        graph.addEdge(Edge.getEdgeByVertexes(v3, v4));

        Set<Edge> edges = graph.getEdges();
        assertEquals(4, edges.size());
    }

    @org.junit.jupiter.api.Test
    void containsVertex() {
        Vertex v1 = Vertex.getVertexByLabel("A");
        Vertex v2 = Vertex.getVertexByLabel("B");
        Vertex v3 = Vertex.getVertexByLabel("C");

        graph.addVertex(v1);
        graph.addVertex(v2);

        assertTrue(graph.containsVertex(v1));
        assertTrue(graph.containsVertex(v2));
        assertFalse(graph.containsVertex(v3));
    }

    @org.junit.jupiter.api.Test
    void removeVertex() {
        Vertex v1 = Vertex.getVertexByLabel("A");
        Vertex v2 = Vertex.getVertexByLabel("B");
        Vertex v3 = Vertex.getVertexByLabel("C");
        Vertex v4 = Vertex.getVertexByLabel("D");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);

        assertTrue(graph.containsVertex(v1));
        assertTrue(graph.containsVertex(v2));
        assertTrue(graph.containsVertex(v3));

        graph.removeVertex(v1);
        assertThrows(NoSuchElementException.class, () -> graph.removeVertex(v4));

        assertFalse(graph.containsVertex(v1));
        assertTrue(graph.containsVertex(v2));
    }

    @org.junit.jupiter.api.Test
    void addEdge() {
        Vertex v1 = Vertex.getVertexByLabel("A");
        Vertex v2 = Vertex.getVertexByLabel("B");
        Vertex v3 = Vertex.getVertexByLabel("C");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);

        Edge edge1 = Edge.getEdgeByVertexes(v1, v2);
        Edge edge2 = Edge.getEdgeByVertexes(v2, v3);
        Edge edge3 = Edge.getEdgeByVertexes(v3, v1);
        Edge edge4 = null;

        graph.addEdge(edge1);
        graph.addEdge(edge2);
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(edge4));

        assertTrue(graph.containsEdge(edge1));
        assertTrue(graph.containsEdge(edge2));
        assertFalse(graph.containsEdge(edge3));
    }

    @org.junit.jupiter.api.Test
    void containsEdge() {
        Vertex v1 = Vertex.getVertexByLabel("A");
        Vertex v2 = Vertex.getVertexByLabel("B");
        Vertex v3 = Vertex.getVertexByLabel("C");
        Edge edge4 = null;

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);

        Edge edge1 = Edge.getEdgeByVertexes(v1, v2);
        Edge edge2 = Edge.getEdgeByVertexes(v2, v3);
        Edge edge3 = Edge.getEdgeByVertexes(v3, v1);

        graph.addEdge(edge1);
        graph.addEdge(edge2);

        assertTrue(graph.containsEdge(edge1));
        assertTrue(graph.containsEdge(edge2));
        assertFalse(graph.containsEdge(edge3));
        assertThrows(IllegalArgumentException.class, () -> graph.containsEdge(edge4));

        graph.removeEdge(edge1);
        assertFalse(graph.containsEdge(edge1));
    }

    @org.junit.jupiter.api.Test
    void removeEdge() {
        Vertex v1 = Vertex.getVertexByLabel("A");
        Vertex v2 = Vertex.getVertexByLabel("B");
        Vertex v3 = Vertex.getVertexByLabel("C");
        Vertex v4 = Vertex.getVertexByLabel("D");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);

        Edge edge1 = Edge.getEdgeByVertexes(v1, v2);
        Edge edge2 = Edge.getEdgeByVertexes(v2, v3);
        Edge edge3 = Edge.getEdgeByVertexes(v3, v1);
        Edge edge4 = null;
        Edge edge5 = Edge.getEdgeByVertexes(v1, v4);

        graph.addEdge(edge1);
        graph.addEdge(edge2);

        assertTrue(graph.containsEdge(edge1));
        assertTrue(graph.containsEdge(edge2));
        assertFalse(graph.containsEdge(edge3));
        assertThrows(NoSuchElementException.class, () -> graph.removeEdge(edge5));
        assertThrows(IllegalArgumentException.class, () -> graph.removeEdge(edge4));

        graph.removeEdge(edge1);
        assertFalse(graph.containsEdge(edge1));

        graph.addEdge(edge1);

        assertTrue(graph.containsEdge(edge1));
    }

    @org.junit.jupiter.api.Test
    void getAdjacent() {
        Vertex v1 = Vertex.getVertexByLabel("A");
        Vertex v2 = Vertex.getVertexByLabel("B");
        Vertex v3 = Vertex.getVertexByLabel("C");
        Vertex v4 = Vertex.getVertexByLabel("D");
        Vertex v5 = Vertex.getVertexByLabel("E");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);

        graph.addEdge(Edge.getEdgeByVertexes(v1, v2));
        graph.addEdge(Edge.getEdgeByVertexes(v1, v3));
        graph.addEdge(Edge.getEdgeByVertexes(v1, v4));
        graph.addEdge(Edge.getEdgeByVertexes(v2, v3));

        Set<Vertex> expectedAdj1 = Set.of(v2, v3, v4);
        Set<Vertex> expectedAdj2 = Set.of(v1, v3);

        assertEquals(expectedAdj1, graph.getAdjacent(v1));
        assertEquals(expectedAdj2, graph.getAdjacent(v2));

        graph.removeEdge(Edge.getEdgeByVertexes(v1, v2));
        Set<Vertex> expectedAdj3 = Set.of(v3, v4);
        assertEquals(expectedAdj3, graph.getAdjacent(v1));

        graph.removeVertex(v1);
        Set<Vertex> expectedAdj4 = Set.of(v3);
        assertEquals(expectedAdj4, graph.getAdjacent(v2));

        assertThrows(NoSuchElementException.class, () -> graph.getAdjacent(v5));
    }

    @org.junit.jupiter.api.Test
    void isAdjacent() {
        Vertex v1 = Vertex.getVertexByLabel("A");
        Vertex v2 = Vertex.getVertexByLabel("B");
        Vertex v3 = Vertex.getVertexByLabel("C");
        Vertex v4 = Vertex.getVertexByLabel("D");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);

        Edge edge1 = Edge.getEdgeByVertexes(v1, v2);
        Edge edge2 = Edge.getEdgeByVertexes(v2, v3);

        graph.addEdge(edge1);
        graph.addEdge(edge2);

        assertTrue(graph.isAdjacent(v1, v2));
        assertFalse(graph.isAdjacent(v1, v3));
        assertTrue(graph.isAdjacent(v2, v3));
        assertThrows(IllegalArgumentException.class, () -> graph.isAdjacent(v1, v4));
        assertThrows(IllegalArgumentException.class, () -> graph.isAdjacent(v4, v1));
    }

    @org.junit.jupiter.api.Test
    void size() {
        Vertex v1 = Vertex.getVertexByLabel("A");
        Vertex v2 = Vertex.getVertexByLabel("B");
        Vertex v3 = Vertex.getVertexByLabel("C");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);

        assertEquals(3, graph.size());

        graph.removeVertex(v1);

        assertEquals(2, graph.size());
    }

    @org.junit.jupiter.api.Test
    void isDirected() {
        assertFalse(graph.isDirected());
    }

    @org.junit.jupiter.api.Test
    void isCyclic() {
        // Crea un nuovo grafo non orientato
        AdjMatrixUndir graph = new AdjMatrixUndir();

        // Aggiungi vertici al grafo
        Vertex v1 = Vertex.getVertexByLabel("A");
        Vertex v2 = Vertex.getVertexByLabel("B");
        Vertex v3 = Vertex.getVertexByLabel("C");
        Vertex v4 = Vertex.getVertexByLabel("D");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);

        Edge edge1 = Edge.getEdgeByVertexes(v1, v3);

        // Aggiungi archi per creare un grafo con un ciclo
        graph.addEdge(Edge.getEdgeByVertexes(v1, v2));
        graph.addEdge(Edge.getEdgeByVertexes(v2, v3));
        graph.addEdge(edge1); // Crea un ciclo

        // Verifica se il grafo contiene un ciclo
        assertTrue(graph.isCyclic());

        // Rimuovi l'arco che forma il ciclo
        graph.removeEdge(edge1);
        graph.removeEdge(Edge.getEdgeByVertexes(v1, v2));

        // Verifica di nuovo se il grafo è ciclico (non dovrebbe più contenere cicli)
        assertFalse(graph.isCyclic());
    }

    @org.junit.jupiter.api.Test
    void isDAG() {
        assertFalse(graph.isDAG());
    }

    @org.junit.jupiter.api.Test
    void getBFSTree() {
        Vertex v1 = Vertex.getVertexByLabel("A");
        Vertex v2 = Vertex.getVertexByLabel("B");
        Vertex v3 = Vertex.getVertexByLabel("C");
        Vertex v4 = Vertex.getVertexByLabel("D");
        Vertex v5 = Vertex.getVertexByLabel("E");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);

        graph.addEdge(Edge.getEdgeByVertexes(v1, v2));
        graph.addEdge(Edge.getEdgeByVertexes(v2, v3));
        graph.addEdge(Edge.getEdgeByVertexes(v3, v4));
        graph.addEdge(Edge.getEdgeByVertexes(v4, v1));

        VisitForest visitForest = graph.getBFSTree(v1);

        for(Vertex vertex : graph.getVertices()) {
            assertEquals(VisitForest.Color.BLACK, visitForest.getColor(vertex));
        }

        assertEquals(VisitForest.VisitType.BFS, visitForest.visitType);
        assertThrows(IllegalArgumentException.class, () ->  graph.getBFSTree(v5));

        Queue<Vertex> queue = new LinkedList<>();
        queue.add(v1);
        int distance = 0;

        while (!queue.isEmpty()) {
            Vertex u = queue.poll();

            for (Vertex v : graph.getAdjacent(u)) {
                if (visitForest.getColor(v) == VisitForest.Color.WHITE) {
                    visitForest.setColor(v, VisitForest.Color.GRAY);
                    visitForest.setParent(v, u);
                    visitForest.setDistance(v, distance);
                    queue.add(v);
                    assertEquals(visitForest.getPartent(v), u);
                    assertEquals(visitForest.getDistance(v), distance);
                }
            }
            distance++;
            visitForest.setColor(u, VisitForest.Color.BLACK);
        }
    }

    @org.junit.jupiter.api.Test
    void getDFSTree() {
        Vertex v1 = Vertex.getVertexByLabel("A");
        Vertex v2 = Vertex.getVertexByLabel("B");
        Vertex v3 = Vertex.getVertexByLabel("C");
        Vertex v4 = Vertex.getVertexByLabel("D");
        Vertex v5 = Vertex.getVertexByLabel("E");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);

        graph.addEdge(Edge.getEdgeByVertexes(v1, v2));
        graph.addEdge(Edge.getEdgeByVertexes(v2, v3));
        graph.addEdge(Edge.getEdgeByVertexes(v3, v4));
        graph.addEdge(Edge.getEdgeByVertexes(v4, v1));

        VisitForest visitForest = graph.getDFSTree(v1);

        for(Vertex vertex : graph.getVertices()) {
            assertEquals(VisitForest.Color.BLACK, visitForest.getColor(vertex));
        }

        assertEquals(VisitForest.VisitType.DFS, visitForest.visitType);
        assertThrows(IllegalArgumentException.class, () ->  graph.getBFSTree(v5));

        Queue<Vertex> queue = new LinkedList<>();
        queue.add(v1);
        int t = 0;

        while (!queue.isEmpty()) {
            Vertex u = queue.poll();

            for (Vertex v : graph.getAdjacent(u)) {
                if (visitForest.getColor(v) == VisitForest.Color.WHITE) {
                    visitForest.setColor(v, VisitForest.Color.GRAY);
                    visitForest.setParent(v, u);
                    visitForest.setStartTime(v, t);
                    queue.add(v);
                    assertEquals(visitForest.getPartent(v), u);
                    assertEquals(visitForest.getStartTime(v), t);
                }
                t++;
            }
            visitForest.setColor(u, VisitForest.Color.BLACK);
        }
    }

    @org.junit.jupiter.api.Test
    void getDFSTOTForest() {
        Vertex v1 = Vertex.getVertexByLabel("A");
        Vertex v2 = Vertex.getVertexByLabel("B");
        Vertex v3 = Vertex.getVertexByLabel("C");
        Vertex v4 = Vertex.getVertexByLabel("D");
        Vertex v5 = Vertex.getVertexByLabel("E");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);

        graph.addEdge(Edge.getEdgeByVertexes(v1, v2));
        graph.addEdge(Edge.getEdgeByVertexes(v2, v3));
        graph.addEdge(Edge.getEdgeByVertexes(v3, v4));
        graph.addEdge(Edge.getEdgeByVertexes(v4, v1));

        VisitForest visitForest = graph.getDFSTree(v1);

        for(Vertex vertex : graph.getVertices()) {
            assertEquals(VisitForest.Color.BLACK, visitForest.getColor(vertex));
        }

        assertEquals(VisitForest.VisitType.DFS, visitForest.visitType);
        assertThrows(IllegalArgumentException.class, () ->  graph.getBFSTree(v5));

        Queue<Vertex> queue = new LinkedList<>();
        queue.add(v1);
        int t = 0;

        while (!queue.isEmpty()) {
            Vertex u = queue.poll();

            for (Vertex v : graph.getAdjacent(u)) {
                if (visitForest.getColor(v) == VisitForest.Color.WHITE) {
                    visitForest.setColor(v, VisitForest.Color.GRAY);
                    visitForest.setParent(v, u);
                    visitForest.setStartTime(v, t);
                    queue.add(v);
                    assertEquals(visitForest.getPartent(v), u);
                    assertEquals(visitForest.getStartTime(v), t);
                }
                t++;
            }
            visitForest.setColor(u, VisitForest.Color.BLACK);
        }
    }

    @org.junit.jupiter.api.Test
    void testGetDFSTOTForest() {
        Vertex v1 = Vertex.getVertexByLabel("A");
        Vertex v2 = Vertex.getVertexByLabel("B");
        Vertex v3 = Vertex.getVertexByLabel("C");
        Vertex v4 = Vertex.getVertexByLabel("D");
        Vertex v5 = Vertex.getVertexByLabel("E");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);

        graph.addEdge(Edge.getEdgeByVertexes(v1, v2));
        graph.addEdge(Edge.getEdgeByVertexes(v2, v3));
        graph.addEdge(Edge.getEdgeByVertexes(v3, v4));
        graph.addEdge(Edge.getEdgeByVertexes(v4, v1));

        VisitForest visitForest = graph.getDFSTree(v1);

        for(Vertex vertex : graph.getVertices()) {
            assertEquals(VisitForest.Color.BLACK, visitForest.getColor(vertex));
        }

        assertEquals(VisitForest.VisitType.DFS, visitForest.visitType);
        assertThrows(IllegalArgumentException.class, () ->  graph.getBFSTree(v5));

        Queue<Vertex> queue = new LinkedList<>();
        queue.add(v1);
        int t = 0;

        while (!queue.isEmpty()) {
            Vertex u = queue.poll();

            for (Vertex v : graph.getAdjacent(u)) {
                if (visitForest.getColor(v) == VisitForest.Color.WHITE) {
                    visitForest.setColor(v, VisitForest.Color.GRAY);
                    visitForest.setParent(v, u);
                    visitForest.setStartTime(v, t);
                    queue.add(v);
                    assertEquals(visitForest.getPartent(v), u);
                    assertEquals(visitForest.getStartTime(v), t);
                }
                t++;
            }
            visitForest.setColor(u, VisitForest.Color.BLACK);
        }
    }

    @org.junit.jupiter.api.Test
    void topologicalSort() {
        assertThrows(UnsupportedOperationException.class, () -> graph.topologicalSort());
    }

    @org.junit.jupiter.api.Test
    void stronglyConnectedComponents() {
        assertThrows(UnsupportedOperationException.class, () -> graph.stronglyConnectedComponents());
    }

    @org.junit.jupiter.api.Test
    void connectedComponents() {
        Vertex v1 = Vertex.getVertexByLabel("A");
        Vertex v2 = Vertex.getVertexByLabel("B");
        Vertex v3 = Vertex.getVertexByLabel("C");
        Vertex v4 = Vertex.getVertexByLabel("D");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);

        graph.addEdge(Edge.getEdgeByVertexes(v1, v2));
        graph.addEdge(Edge.getEdgeByVertexes(v2, v3));
        graph.addEdge(Edge.getEdgeByVertexes(v3, v4));
        graph.addEdge(Edge.getEdgeByVertexes(v1, v3));

        var components = graph.connectedComponents();
        assertEquals(1, components.size());
        components.forEach(c -> assertEquals(4, c.size()));

    }
}