package csc656;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Graph
{
    /**
     * List of all vertices in the graph
     */
    private final HashMap<String, Vertex> vertices;
    /**
     * List of all edges in the graph
     */
    private final HashMap<String, Edge> edges;
    /**
     * The alphabet over which the graph will be built
     */
    private char[] alphabet;
    
    /**
     * Constructor for a graph with no specifications
     */
    public Graph()
    {
        this.vertices = new HashMap<>();
        this.edges = new HashMap<>();
        this.alphabet = null;
    }
    
    /**
     * New constructor requiring a specific alphabet to be specified
     * @param alphabet over which de Bruijn graph will be constructed
     */
    public Graph(char[] alphabet){
        this.alphabet = alphabet;
        this.vertices = new HashMap<>();
        this.edges = new HashMap<>();
    }
    
    /**
     * Recursively constructs a de Bruijn graph from alphabet and n-length 
     * subwords
     * @param n the dimensionality of the graph (desired length of subwords) 
     */
    public void buildGraph(int n){
        StringBuilder sb = new StringBuilder();
        //create initial vertex
        for(int i=0; i<n; i++){
            sb.append(this.alphabet[0]);
        }
        Vertex root = new Vertex(sb.toString());
        this.addVertex(root);
        
        //recursively build de Bruijn Graph
        populate(root);
    }
    
    /**
     * recursive helper method that constructs the de Bruijn graph
     * @param vertex 
     */
    private void populate(Vertex vertex){
        if(vertex.getOutEdgesCount() >= this.alphabet.length){
            return;
        }
        for(char character : this.alphabet){
            Vertex destination;
            String edgeLabel = vertex.getLabel()+character;
            String destLabel = edgeLabel.substring(1, edgeLabel.length());
            //assign destination or create vertex
            if(this.vertices.containsKey(destLabel)){
                destination = this.vertices.get(destLabel);
            }else{
                destination = new Vertex(destLabel);
                this.addVertex(destination);
            }
            //create new edge and link to graph
            addEdge(vertex, destination, edgeLabel);
            
            //populate destination vertex
            populate(destination);
        }
    }

    /**
     * Method checks for existence of an edge in the graph 
     * @param e the desired edge
     * @return true if edge is found
     */
    public boolean containsEdge(Edge e)
    {
        if(e.getStartVertex()== null || e.getEndVertex()== null)
        {
            return false;
        }
        return this.edges.containsKey(e.getLabel());
    }

    /**
     * Method removes an edge from the graph, along with the references to it
     * from its origin and destination vertices
     * @param e the edge to be removed
     * @return the removed edge if it was removed successfully or null
     */
    public Edge removeEdge(Edge e)
    {
        e.getStartVertex().removeEdge(e);
        e.getEndVertex().removeEdge(e);
        return this.edges.remove(e.getLabel());
    }

    /**
     * Method adds an edge to the graph
     * @param start vertex of origin
     * @param end destination vertex
     * @param label name of the edge
     */
    public void addEdge(Vertex start, Vertex end, String label)
    {
        Edge e = new Edge(start, end, label);
        edges.put(label, e);
        start.addOutEdge(e);
        end.addInEdge(e);
    }
    
    /**
     * Method returns keys of edges in graph
     * @return set of edges
     */
    public Set<Edge> getEdges()
    {
        return new HashSet<>(this.edges.values());
    }
    
    /**
     * Method returns the number of edges contained in the graph 
     * @return number of edges
     */
    public int getNumEdges(){
        return this.edges.size();
    }
    
    /**
     * Method checks for the existence of a vertex in the graph
     * @param vertex
     * @return 
     */
    public boolean containsVertex(Vertex vertex)
    {
        return this.vertices.get(vertex.getLabel()) != null;
    }
    
    /**
     * Method returns a vertex
     * @param label String label of the desired vertex
     * @return the desired vertex or null if no mapping was found
     */
    public Vertex getVertex(String label)
    {
        return vertices.get(label);
    }

    /**
     * Method adds a vertex to the graph
     * @param vertex 
     */
    public void addVertex(Vertex vertex)
    {
        vertices.put(vertex.getLabel(), vertex);
    }
    
    /**
     * Method returns keys of vertices in graph
     * @return set of keys for vertex map 
     */
    public Set<String> vertexKeys()
    {
        return this.vertices.keySet();
    }
    
    /**
     * Method returns the number of vertices contained in the graph 
     * @return number of vertices
     */
    public int getNumVertices(){
        return this.vertices.size();
    }
 
    /**
     * Method sets the alphabet the graph is built over
     * @param alpha the desired alphabet you want the graph to use 
     */
    public void setAlphabet(char[] alpha){
        this.alphabet = alpha;
    }
     
    /**
     * Method returns the graphs underlying alphabet
     * @return 
     */
    public char[] getAlphabet(){
        return this.alphabet;
    }
    
    /**
     * Method creates a string representation of the graph
     * @return String of the graph
     */
    public String printEdges()
    {
        /**
         * TODO: Maybe change the output to something more readable? 
         * (edges in order?)
         */
        String tmp = "";
        for(String key : edges.keySet())
        {
            tmp += edges.get(key).getLabel();
            tmp += ":";
            tmp += edges.get(key).getStartVertex().getLabel();
            tmp += "->";
            tmp += edges.get(key).getEndVertex().getLabel();
            tmp += "\n";
        }
        return tmp;
    }
}