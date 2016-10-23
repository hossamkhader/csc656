package csc656;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Graph {

    /**
     * List of all vertices in the graph
     */
    private final HashMap<String, Vertex> vertices;
    /**
     * List of all edges in the graph
     */
    private final HashMap<String, Edge> edges;
    
    /* 
     * Creates an empty graph that requires construction
     */
    public Graph(){
        this.vertices = new HashMap<>();
        this.edges = new HashMap<>();
    }
    /**
     * Copy Constructor
     * @param graph
     */
    public Graph(Graph graph) {
        this.edges = new HashMap<>(graph.edges);
        this.vertices = new HashMap<>(graph.vertices);
    }

/*
  original method author: Michael Branon
  rewrite/refactoring: Jonathon Tovey
*/

    /**
     * Constructs a graph with vertices of length d. First constructs each edge
     * and then attaches it to the appropriate vertices, constructing them if
     * necessary
     * @param n length of edges/subwords
     */
    public void buildGraph(int n) {
        String[] edgeStrs = new String[(int) Math.pow((double) 2, (double) n)];
        // debug // System.out.println((int)Math.pow((double)2,(double)n));
        String frmt = "%" + n + "s";
        // debug // System.out.println(frmt);
        for (int i = 0; i < edgeStrs.length; i++) {
            edgeStrs[i] = String.format(frmt, Integer.toBinaryString(i))
                    .replace(' ', '0');
            // debug // System.out.println("creating edge " + edgeStrs[i]);
            
            Vertex origin;
            String originLabel = edgeStrs[i].substring(0, (edgeStrs[i].length() - 1));
            if (this.vertices.containsKey(originLabel)) {
                origin = this.vertices.get(originLabel);
            } else {
                origin = new Vertex(originLabel);
                this.addVertex(origin);
            }

            Vertex destination;
            String destLabel = edgeStrs[i].substring(1);
            if (this.vertices.containsKey(destLabel)) {
                destination = this.vertices.get(destLabel);
            } else {
                destination = new Vertex(destLabel);
                this.addVertex(destination);
            }

            addEdge(origin, destination, edgeStrs[i]);
            /// debug // System.out.println("adding edge " + edgeStrs[i]);	
        }
    }

    /**
     * Method checks for existence of an edge in the graph
     *
     * @param e the desired edge
     * @return true if edge is found
     */
    public boolean containsEdge(Edge e) {
        if (e.getStartVertex() == null || e.getEndVertex() == null) {
            return false;
        }
        return this.edges.containsKey(e.getLabel());
    }

    /**
     * Method removes an edge from the graph, along with the references to it
     * from its origin and destination vertices
     *
     * @param e the edge to be removed
     * @return the removed edge if it was removed successfully or null
     */
    public Edge removeEdge(Edge e) {
        e.getStartVertex().removeEdge(e);
        e.getEndVertex().removeEdge(e);
        return this.edges.remove(e.getLabel());
    }

    /**
     * Method adds an edge to the graph
     *
     * @param start vertex of origin
     * @param end destination vertex
     * @param label name of the edge
     */
    public void addEdge(Vertex start, Vertex end, String label) {
        Edge e = new Edge(start, end, label);
        edges.put(label, e);
        start.addOutEdge(e);
        end.addInEdge(e);
    }

    /**
     * Method returns keys of edges in graph
     *
     * @return set of edges
     */
    public Set<Edge> getEdges() {
        return new HashSet<>(this.edges.values());
    }

    /**
     * Method returns the number of edges contained in the graph
     *
     * @return number of edges
     */
    public int getNumEdges() {
        return this.edges.size();
    }

    /**
     * Method checks for the existence of a vertex in the graph
     *
     * @param vertex
     * @return
     */
    public boolean containsVertex(Vertex vertex) {
        return this.vertices.get(vertex.getLabel()) != null;
    }

    /**
     * Method returns a vertex
     *
     * @param label String label of the desired vertex
     * @return the desired vertex or null if no mapping was found
     */
    public Vertex getVertex(String label) {
        return vertices.get(label);
    }

    /**
     * Method adds a vertex to the graph
     *
     * @param vertex
     */
    public void addVertex(Vertex vertex) {
        vertices.put(vertex.getLabel(), vertex);
    }

    /**
     * Method returns keys of vertices in graph
     *
     * @return set of keys for vertex map
     */
    public Set<String> vertexKeys() {
        return this.vertices.keySet();
    }

    /**
     * Method returns the number of vertices contained in the graph
     *
     * @return number of vertices
     */
    public int getNumVertices() {
        return this.vertices.size();
    }

    /**
     * Method creates a string representation of the graph
     *
     * @return String of the graph
     */
    public String printEdges() {
        /**
         * TODO: Maybe change the output to something more readable? (edges in
         * order?)
         */
        String tmp = "";
        for (String key : edges.keySet()) {
            tmp += edges.get(key).getLabel();
            tmp += " : ";
            tmp += edges.get(key).getStartVertex().getLabel();
            tmp += " --> ";
            tmp += edges.get(key).getEndVertex().getLabel();
            tmp += "\n";
        }
        return tmp;
    }

/*
  method author: Jonathon Tovey
*/
    public String printVertices() {

        String tmp = "";
        for (String key : vertices.keySet()) {
            tmp += key + " ";
        }
        return tmp;

    }

/*
  method author: Jonathon Tovey
*/

    public void removeVertex(Vertex v) {

        this.vertices.remove(v.getLabel());

        // debug //
        System.out.println("Vertex " + v.getLabel() + " removed");
    }

/*
  method author: Jonathon Tovey
*/

    /**
     * Method compresses a graph (this)
     * @param seed
     * @param n
     * @return a compressed copy of the graph
     */
    public Graph compressGraph(String seed, int n) {

        Graph graphCopy = new Graph(this);

        String[] subwords = SeedToSubwords.swOfLength(seed, n);

        for (String sw : subwords) {

            graphCopy.removeEdge(edges.get(sw));

            // debug //
            System.out.println("Edge " + sw + " removed");
        }
        
        //add seed edge
        Vertex sOrigin = graphCopy.getVertex(seed.substring(0, n-1));
	// debug // System.out.println(sOrigin.getLabel());
        Vertex sDestination = 
                this.getVertex(seed.substring(seed.length() - n + 1));
	// debug // System.out.println(sDestination.getLabel());

	// debug //
	System.out.print("Adding seed edge " + seed);
	System.out.println(" from " + sOrigin.getLabel() + " to " + sDestination.getLabel());

        graphCopy.addEdge(sOrigin, sDestination, seed);
        
        Graph graphCopyIter = new Graph(graphCopy);
        for (String vStr : graphCopyIter.vertexKeys()) {

            Vertex v = graphCopy.getVertex(vStr);

            // debug // System.out.println("Vertex " + vStr + " with " + v.getInEdgesCount() + " in, " + v.getOutEdgesCount() + " out");
            // if vertex has zero edges, remove
            if (v.getInEdgesCount() == 0 && v.getOutEdgesCount() == 0) {
                graphCopy.removeVertex(v);
            }
            // debug // System.out.println(graphCopy.printVertices());
        }
        return graphCopy;
    }

}
