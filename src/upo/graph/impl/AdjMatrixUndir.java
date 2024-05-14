package upo.graph.impl;

import java.util.*;

import upo.graph.base.Edge;
import upo.graph.base.Graph;
import upo.graph.base.Vertex;
import upo.graph.base.VisitForest;

public class AdjMatrixUndir extends GraphVertexMapping implements Graph {

    protected ArrayList<ArrayList<Double>> matrix;

    public AdjMatrixUndir() {
        matrix = new ArrayList<>();
    }

    @Override
    public int addVertex(Vertex vertex) {
        if(vertex != null && !vertexList.contains(vertex)) {
            addVertexIndex(vertex);
            ArrayList<Double> newRow = new ArrayList<>();
            int i = 0;
            int n = 1;
            for(i = 0; i < vertexList.size(); i++) {
                newRow.add(0.0);
            }
            matrix.add(newRow);
            for(i = 0; i < vertexList.size() - 1; i++) {
                matrix.get(i).add(0.0);
            }
            return i;
        }
        return -1;
    }

    @Override
    public Set<Vertex> getVertices() {
        return new HashSet<>(vertexList);
    }

    @Override
    public Set<Edge> getEdges() {

        Set<Edge> edges = new HashSet<>();

        for (int i = 0; i < matrix.size(); i++) {
            for (int j = i + 1; j < matrix.get(i).size(); j++) {
                if (matrix.get(i).get(j) != 0) {
                    Vertex source = getVertex(i);
                    Vertex target = getVertex(j);
                    Edge edge = Edge.getEdgeByVertexes(source, target);
                    edges.add(edge);
                }
            }
        }
        return edges;
    }

    @Override
    public boolean containsVertex(Vertex vertex) {
        return vertexList.contains(vertex);
    }

    @Override
    public void removeVertex(Vertex vertex) throws NoSuchElementException {
        if(!vertexList.contains(vertex)) throw new NoSuchElementException();
        int index = getIndexOf(vertex);
        vertexList.remove(index);
        matrix.remove(index);
        for (ArrayList<Double> row : matrix) {
            row.remove(index);
        }
    }

    @Override
    public void addEdge(Edge edge) throws IllegalArgumentException {
        if(edge == null) throw new IllegalArgumentException("Edge is null");

        Vertex source = edge.getSource();
        Vertex target = edge.getTarget();

        int indexSource = getIndexOf(source);
        int indexTarget = getIndexOf(target);

        matrix.get(indexSource).set(indexTarget, 1.0);
        matrix.get(indexTarget).set(indexSource, 1.0);
    }

    @Override
    public boolean containsEdge(Edge edge) throws IllegalArgumentException {
        if(edge == null) throw new IllegalArgumentException("Edge is null");
        Vertex source = edge.getSource();
        Vertex target = edge.getTarget();

        int indexSource = getIndexOf(source);
        int indexTarget = getIndexOf(target);

        return matrix.get(indexSource).get(indexTarget) != 0;
    }

    @Override
    public void removeEdge(Edge edge) throws IllegalArgumentException, NoSuchElementException {
        if(edge == null) throw new IllegalArgumentException("Edge is null");
        Vertex source = edge.getSource();
        Vertex target = edge.getTarget();
        if(!vertexList.contains(source) || !vertexList.contains(target)) throw new NoSuchElementException("Not existing edge");

        int indexSource = getIndexOf(source);
        int indexTarget = getIndexOf(target);

        if(matrix.get(indexSource).get(indexTarget) == 0) {
            throw new IllegalArgumentException("Not existing edge");
        } else {
            matrix.get(indexSource).set(indexTarget, 0.0);
        }
    }

    @Override
    public Set<Vertex> getAdjacent(Vertex vertex) throws NoSuchElementException {

        if(!vertexList.contains(vertex)) throw new NoSuchElementException();

        Set<Vertex> vertices = new HashSet<>();
        int i = 0;

        int index = getIndexOf(vertex);
        ArrayList<Double> row = matrix.get(index);

        for(Double col : row) {
            if(col != 0) {
                Vertex adj = getVertex(i);
                vertices.add(adj);
            }
            i++;
        }
        return vertices;
    }

    @Override
    public boolean isAdjacent(Vertex targetVertex, Vertex sourceVertex) throws IllegalArgumentException {
        if(!vertexList.contains(targetVertex) || !vertexList.contains(sourceVertex)) throw new IllegalArgumentException();

        int indexSource = getIndexOf(sourceVertex);
        int indexTarget = getIndexOf(targetVertex);

        return matrix.get(indexSource).get(indexTarget) != 0;
    }

    @Override
    public int size() {
        return vertexList.size();
    }

    @Override
    public boolean isDirected() {
        return false;
    }

    @Override
    public boolean isCyclic() {
        VisitForest forest = new VisitForest(this, null);
        for (Vertex element : vertexList) {
            if ((forest.getColor(element) == VisitForest.Color.WHITE) && visitRicCycle(forest, element))
                return true;
        }
        return false;
    }

    private boolean visitRicCycle(VisitForest forest, Vertex element) {
        forest.setColor(element, VisitForest.Color.GRAY);
        for (Vertex adj : getAdjacent(element)) {
            if (forest.getColor(adj) == VisitForest.Color.WHITE) {
                forest.setParent(adj, element);
                if (visitRicCycle(forest, adj))
                    return true;
            } else if (forest.getColor(adj) == VisitForest.Color.GRAY && !adj.equals(forest.getPartent(element)))
                return true;
        }
        forest.setColor(element, VisitForest.Color.BLACK);
        return false;
    }

    @Override
    public boolean isDAG() {
        return false;
    }

    @Override
    public VisitForest getBFSTree(Vertex startingVertex)
            throws UnsupportedOperationException, IllegalArgumentException {

        return getBFSTree(startingVertex, new VisitForest(this, VisitForest.VisitType.BFS));
    }

    private VisitForest getBFSTree(Vertex startingVertex, VisitForest visitForest)
            throws UnsupportedOperationException, IllegalArgumentException {

        if(!vertexList.contains(startingVertex)) throw new IllegalArgumentException("Starting vertex not found");

        int distance = 0;

        // Inizia la visita BFS
        Queue<Vertex> queue = new LinkedList<>();
        visitForest.setColor(startingVertex, VisitForest.Color.GRAY);
        queue.add(startingVertex);

        while (!queue.isEmpty()) {
            Vertex u = queue.poll();

            for (Vertex v : getAdjacent(u)) {
                if (visitForest.getColor(v) == VisitForest.Color.WHITE) {
                    visitForest.setColor(v, VisitForest.Color.GRAY);
                    visitForest.setParent(v, u);
                    visitForest.setDistance(v, distance);
                    queue.add(v);
                }
            }
            distance++;
            visitForest.setColor(u, VisitForest.Color.BLACK);
        }


        return visitForest;
    }

    @Override
    public  VisitForest getDFSTree(Vertex startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
        return getDFSTree(startingVertex, new VisitForest(this, VisitForest.VisitType.DFS));
    }

    public VisitForest getDFSTree(Vertex startingVertex, VisitForest visitForest)
            throws UnsupportedOperationException, IllegalArgumentException {

        if (!vertexList.contains(startingVertex)) {
            throw new IllegalArgumentException("Starting vertex not found");
        }


        Stack<Vertex> stack = new Stack<>();
        stack.push(startingVertex);

        int t = 0;

        while (!stack.isEmpty()) {
            Vertex u = stack.pop();

            if (visitForest.getColor(u) != VisitForest.Color.BLACK) {
                visitForest.setColor(u, VisitForest.Color.GRAY);
                visitForest.setStartTime(u, t++);

                for (Vertex v : getAdjacent(u)) {
                    if (visitForest.getColor(v) == VisitForest.Color.WHITE) {
                        visitForest.setParent(v, u);
                        stack.push(v);
                    }
                }

                visitForest.setColor(u, VisitForest.Color.BLACK);
            }
        }

        return visitForest;
    }

    @Override
    public VisitForest getDFSTOTForest(Vertex startingVertex)
            throws UnsupportedOperationException, IllegalArgumentException {

        VisitForest visitForest = getDFSTree(startingVertex);

            for(Vertex vertex : vertexList) {
                if(visitForest.getColor(vertex) == VisitForest.Color.WHITE) {
                    visitForest = getDFSTree(vertex, visitForest);
                }
            }
        return visitForest;
    }

    @Override
    public VisitForest getDFSTOTForest(Vertex[] vertexOrdering)
            throws UnsupportedOperationException, IllegalArgumentException {;

        for(Vertex vertex : vertexOrdering) {
            VisitForest visitForest = getDFSTree(vertex);
            if(visitForest.getColor(vertex) == VisitForest.Color.WHITE) {
                visitForest = getDFSTree(vertex, visitForest);
            }
        }
        return null;
    }

    @Override
    public Vertex[] topologicalSort() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public Set<Set<Vertex>> stronglyConnectedComponents() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public Set<Set<Vertex>> connectedComponents() throws UnsupportedOperationException {
        Set<Set<Vertex>> componentsList = new HashSet<>();
        Set<Vertex> toAdd = new HashSet<>();
        VisitForest visit = new VisitForest(this, VisitForest.VisitType.DFS);
        for (Vertex element : vertexList)
            if (visit.getColor(element) == VisitForest.Color.WHITE)
                toAdd.add(element);
                componentsList.add(toAdd);
        return componentsList;
    }
}
