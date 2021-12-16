package material.graphs;

import java.util.*;

import material.Position;
import material.tree.Tree;
import material.tree.narytree.LinkedTree;
import material.utils.Pair;

/**
 *
 * @author jvelez
 * @param <V>
 * @param <E>
 */
public class Recorridos<V, E> {

    /**
     * Devuelve el árbol que se genera al realizar el recorrido en profundidad
     * 
     * @param graph
     * @param source
     * @return 
     */
    
    public Tree<Vertex<V>> depthTravel(Graph<V,E> graph, Vertex<V> source){
        LinkedTree<Vertex<V>> out = new LinkedTree<>();
        HashSet<Vertex<V>> visitados = new HashSet<>();
        Stack<Pair<Vertex<V>, Position<Vertex<V>>>> stack = new Stack<>();
        stack.add(new Pair<>(source, null));
        while (!stack.isEmpty()){
            Pair<Vertex<V>, Position<Vertex<V>>> pair = stack.pop();
            Vertex<V> child = pair.getKey();
            Position<Vertex<V>> parent = pair.getValue();
            if(!visitados.contains(child)){
                visitados.add(child);
                Position<Vertex<V>> newPos;
                if(parent==null){
                    newPos = out.addRoot(child);
                }
                else{
                    newPos = out.add(child, parent);
                }
                for(Edge<E> edge : graph.incidentEdges(child)){
                    Vertex<V> newVertex = graph.opposite(child, edge);
                    stack.add(new Pair<>(newVertex, newPos));
                }
            }
        }
        return out;

    }
    
    /**
     * Devuelve el árbol que se genera al realizar el recorrido en anchura
     * 
     * @param graph
     * @param source
     * @return 
     */
    
    public Tree<Vertex<V>> widthTravel(Graph<V,E> graph, Vertex<V> source){
        LinkedTree<Vertex<V>> out = new LinkedTree<>();
        HashSet<Vertex<V>> visitados = new HashSet<>();
        Queue<Pair<Vertex<V>, Position<Vertex<V>>>> queue = new LinkedList<>();
        queue.add(new Pair<>(source, null));
        while (!queue.isEmpty()){
            Pair<Vertex<V>, Position<Vertex<V>>> pair = queue.poll();
            Vertex<V> child = pair.getKey();
            Position<Vertex<V>> parent = pair.getValue();
            if(!visitados.contains(child)){
                visitados.add(child);
                Position<Vertex<V>> newPos;
                if(parent==null){
                    newPos = out.addRoot(child);
                }
                else{
                    newPos = out.add(child, parent);
                }
                for(Edge<E> edge : graph.incidentEdges(child)){
                    Vertex<V> newVertex = graph.opposite(child, edge);
                    queue.add(new Pair<>(newVertex, newPos));
                }
            }
        }
        return out;
    }
    
    /**
     * Get the path between two vertex with minimun number of nodes.
     *
     * @param graph
     * @param startVertex
     * @param endVertex
     * @return The edge list
     */
    public List<Edge<E>> getPath(Graph<V, E> graph, Vertex<V> startVertex, Vertex<V> endVertex) {
        LinkedList<Edge<E>> out = new LinkedList<>();
        HashMap<Vertex<V>, Edge<E>> map = new HashMap<>();
        Queue<Pair<Vertex<V>, Edge<E>>>queue = new LinkedList<>();
        queue.add(new Pair<>(startVertex, null));
        while (!queue.isEmpty()&&!map.containsKey(endVertex)) {
            Pair<Vertex<V>, Edge<E>> pair = queue.poll();
            Vertex<V> vertex = pair.getKey();
            Edge<E> edge = pair.getValue();
            if (!map.containsKey(vertex)) {
                map.put(vertex, edge);
                for (Edge<E> e : graph.incidentEdges(vertex)) {
                    Vertex<V> newVertex = graph.opposite(vertex, e);
                    queue.add(new Pair<>(newVertex, e));
                }
            }
        }
        if(map.containsKey(endVertex)){
            Vertex<V> vertex = endVertex;
            Edge<E> edge = map.get(endVertex);
            while (edge!=null){
                out.addFirst(edge);
                vertex = graph.opposite(vertex, edge);
                edge = map.get(vertex);
            }
        }
        return out;
    }

    /**
     * Devuelve el conjunto de vértices visitados al hacer un recorrido en profundidad partiendo de root
     * @param g
     * @param root
     * @return 
     */
    public Set<Vertex<V>> traverse(Graph<V, E> g, Vertex<V> root) {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
