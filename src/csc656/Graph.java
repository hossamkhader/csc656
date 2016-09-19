package csc656;

import java.util.ArrayList;
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
     * Number of vertices
     */
    private int numVerts;
    
    /**
     * Number of edges
     */
    private int numEdges;
    
    /**
     * 
     */
    private char[] alphabet;
    
    public Graph()
    {
        this.vertices = new HashMap<>();
        this.edges = new HashMap<>();
        numVerts = 0;
        numEdges = 0;
        
    }
    
    public Graph(char[] alphabet){
        this.alphabet = alphabet;
        this.vertices = new HashMap<>();
        this.edges = new HashMap<>();
        numVerts = 0;
        numEdges = 0;
    }
	     
    public void buildGraph(int n){
        StringBuilder sb = new StringBuilder();
        //create initial vertex
        for(int i=0; i<n; i++){
            sb.append(this.alphabet[0]);
        }
        Vertex root = new Vertex(sb.toString());
        this.vertices.put(root.getLabel(), root);
        this.numVerts++;
        
        //recursively build de Bruijn Graph
        populate(root);
    }
    
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
                this.vertices.put(destLabel, destination);
                numVerts++;
            }
            //create new edge and link to graph
            addEdge(vertex, destination, edgeLabel);
            
            //populate destination vertex
            populate(destination);
        }
    }
    
    public Graph(ArrayList<Vertex> vertices)
    {
        this.vertices = new HashMap<>();
        this.edges = new HashMap<>();
        vertices.stream().forEach((v) -> {
            this.vertices.put(v.getLabel(), v);
        });
    }

    public void addEdge(Vertex start, Vertex end, String label)
    {
        Edge e = new Edge(start, end, label);
        edges.put(label, e);
        start.addOutEdge(e);
        end.addInEdge(e);
        this.numEdges++;
    }

    public boolean containsEdge(Edge e)
    {
        if(e.getStartVertex()== null || e.getEndVertex()== null)
        {
            return false;
        }
        return this.edges.containsKey(e.hashCode());
    }

    public Edge removeEdge(Edge e)
    {
        e.getStartVertex().removeEdge(e);
        e.getEndVertex().removeEdge(e);
        return this.edges.remove(e.hashCode());
    }
	     
    public boolean containsVertex(Vertex vertex)
    {
        return this.vertices.get(vertex.getLabel()) != null;
    }

    public Vertex getVertex(String label)
    {
        return vertices.get(label);
    }

    public void addVertex(Vertex vertex)
    {
        vertices.put(vertex.getLabel(), vertex);
    }
    
    public Set<String> vertexKeys()
    {
        return this.vertices.keySet();
    }

    public Set<Edge> getEdges()
    {
        return new HashSet<>(this.edges.values());
    }
     
    public String printEdges()
    {
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