package upo.graph.impl;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import upo.graph.base.Edge;
import upo.graph.base.Vertex;
import upo.graph.base.WeightedGraph;

public class AdjMatrixUndirWeighted extends AdjMatrixUndir implements WeightedGraph {

    public AdjMatrixUndirWeighted() {
        matrix = new ArrayList<>();
    }

    public int addVertexWeighted(Vertex vertex) {
        if(vertex != null && !vertexList.contains(vertex)) {
            addVertexIndex(vertex);
            ArrayList<Double> newRow = new ArrayList<Double>();
            int i = 0;
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

    public void addEdgeWeighted(Edge edge, Double weight) throws IllegalArgumentException {
        if(edge == null) throw new IllegalArgumentException("Edge is null");

        Vertex source = edge.getSource();
        Vertex target = edge.getTarget();

        int indexSource = getIndexOf(source);
        int indexTarget = getIndexOf(target);

        matrix.get(indexSource).set(indexTarget, weight);
        matrix.get(indexTarget).set(indexSource, weight);
    }

    @Override
    public double getEdgeWeight(Edge edge) throws IllegalArgumentException, NoSuchElementException {
        if(edge == null) throw new NoSuchElementException("Edge is null");
        Vertex source = edge.getSource();
        Vertex target = edge.getTarget();

        if(!vertexList.contains(source) || !vertexList.contains(target)) throw new IllegalArgumentException("One or both vertices not found");
        if(matrix.get(getIndexOf(source)).get(getIndexOf(target)) == 0.0) throw new NoSuchElementException("Edge not found");

        return matrix.get(getIndexOf(source)).get(getIndexOf(target));
    }

    @Override
    public void setEdgeWeight(Edge edge, double weight) throws IllegalArgumentException, NoSuchElementException {
        if(edge == null) throw new IllegalArgumentException("Edge is null");

        Vertex source = edge.getSource();
        Vertex target = edge.getTarget();

        int indexSource = getIndexOf(source);
        int indexTarget = getIndexOf(target);

        matrix.get(indexSource).set(indexTarget, weight);
    }

    @Override
    public WeightedGraph getBellmanFordShortestPaths(Vertex startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public WeightedGraph getDijkstraShortestPaths(Vertex startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public WeightedGraph getPrimMST(Vertex startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public WeightedGraph getKruskalMST() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public WeightedGraph getFloydWarshallShortestPaths() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported");
    }
}
