package upo.graph.algotechniques;

import upo.graph.base.Graph;
import upo.graph.base.Edge;
import upo.graph.base.Vertex;
import upo.graph.base.WeightedGraph;

import java.util.*;

public class Approximate {

    /** Calcola un ciclo Hamiltoniano di peso al più 2 volte quello di un ciclo Hamiltoniano minimi
     * utilizzando l'algoritmo <strong>approssimato</strong> visto a lezione.
     *
     * @param graph il grafo sul quale applicare l'algoritmo
     * @return una lista di vertici che rappresenta il ciclo Hamiltoniano calcolato (es. <A, D, C, A>)
     * @throws IllegalArgumentException nel caso in cui il grafo non sia completo o sia orientato (evitate,
     * per semplicità, di verificare la disuguaglianza triangolare su tutte le coppie di archi)
     */
    public static List<Vertex> approxTSP(WeightedGraph graph) throws IllegalArgumentException {
        // Check if the graph is complete and undirected
        Set<Vertex> vertices = graph.getVertices();
        for (Vertex v : vertices) {
            if (graph.getEdges(v).size() != vertices.size() - 1) {
                throw new IllegalArgumentException("Graph is not complete");
            }
        }

        // Step 1: Create a Minimum Spanning Tree (MST) using Prim's algorithm
        Set<Vertex> mstSet = new HashSet<>();
        List<Edge> mstEdges = new ArrayList<>();
        PriorityQueue<Edge> edgeQueue = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        Vertex start = vertices.iterator().next();
        mstSet.add(start);
        edgeQueue.addAll(graph.getEdges(start));

        while (mstSet.size() < vertices.size()) {
            Edge edge = edgeQueue.poll();
            if (edge == null) break;

            Vertex newVertex = null;
            if (!mstSet.contains(edge.getSource())) {
                newVertex = edge.getSource();
            } else if (!mstSet.contains(edge.destination)) {
                newVertex = edge.destination;
            }

            if (newVertex != null) {
                mstSet.add(newVertex);
                mstEdges.add(edge);
                for (Edge e : graph.getEdges(newVertex)) {
                    if (!mstSet.contains(e.getOther(newVertex))) {
                        edgeQueue.add(e);
                    }
                }
            }
        }

        // Step 2: Perform a preorder traversal of the MST to get a Hamiltonian circuit
        List<Vertex> tspPath = new ArrayList<>();
        Set<Vertex> visited = new HashSet<>();
        Stack<Vertex> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            Vertex current = stack.pop();
            if (!visited.contains(current)) {
                visited.add(current);
                tspPath.add(current);
                for (Edge e : graph.getEdges(current)) {
                    Vertex next = e.getOther(current);
                    if (!visited.contains(next)) {
                        stack.push(next);
                    }
                }
            }
        }
        tspPath.add(start); // Make it a cycle by adding the start vertex at the end

        return tspPath;
    }

    /** Calcola un ciclo Hamiltoniano utilizzando l'algoritmo <strong>di ricerca locale</strong> visto a
     * lezione (k scambi, con k = 2).
     * </br>NOTA: nonostante l'algoritmo possa essere applicato solo a grafi non orientati, qui per
     * semplicità vi viene chiesto di applicarlo anche a grafi orientati. Nel caso in cui il vostro grafo
     * sia orientato, considerate <u,v> equivalente ad (u,v). Prima di applicare l'algoritmo, preprocessate
     * il vostro grafo: se sono presenti sia l'arco <u,v> che l'arco <v,u>, eliminate uno dei 2 (quello con
     * il peso maggiore).
     *
     * @param graph il grafo sul quale applicare l'algoritmo
     * @return una lista di vertici che rappresenta il ciclo Hamiltoniano calcolato (es. <A, D, C, A>)
     * @throws IllegalArgumentException nel caso in cui il grafo non sia completo
     */
    public static List<Vertex> localSearchTSP(WeightedGraph graph) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /** Calcola una copertura di vertici di cardinalità al più 2 volte quello di una copertura minima,
     * utilizzando l'algoritmo approssimato visto a lezione.
     * </br>NOTA: nonostante l'algoritmo possa essere applicato solo a grafi non orientati, qui per
     * semplicità vi viene chiesto di applicarlo anche a grafi orientati. Nel caso in cui il vostro grafo
     * sia orientato, considerate <u,v> equivalente ad (u,v). Prima di applicare l'algoritmo, preprocessate
     * il vostro grafo: se sono presenti sia l'arco <u,v> che l'arco <v,u>, eliminate uno dei 2.
     *
     * @param graph il grafo sul quale applicare l'algoritmo
     * @return una collezione contenente la copertura trovata
     */
    public static Collection<Vertex> approxCover(Graph graph) {
        throw new UnsupportedOperationException("Not yet implemented");
    }



}
