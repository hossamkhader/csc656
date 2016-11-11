package csc656;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Class Authors: Hossam Khader, Michael Branon
 */
public class Vertex
{
    /**
     * List of incoming edges
     */
    private final ArrayList<Edge> inEdges;
    /**
     * List of outgoing edges
     */
    private final ArrayList<Edge> outEdges;
    /**
     * Label for vertex
     */
    private final String label;
    /**
     * Classification for vertex
     */
    private VertexClassification classification;
    /**
     * Constructs a new vertex
     * @param label 
     */
    public Vertex(String label)
    {
        this.label = label;
        this.inEdges = new ArrayList<>();
        this.outEdges = new ArrayList<>();
    }
    
    /**
     * adds an in edge to the vertex
     * @param edge
     * @return true if edge was added successfully
     */
    public boolean addInEdge(Edge edge){
        if(this.inEdges.contains(edge)){
            return false;
        }else{
            this.inEdges.add(edge);
            return true;
        }
    }
    
    /**
     * adds an out edge the the vertex
     * @param edge
     * @return true if edge was added successfully
     */
    public boolean addOutEdge(Edge edge){
        if(this.outEdges.contains(edge)){
            return false;
        }else{
            this.outEdges.add(edge);
            return true;
        }
    }
    
    /**
     * Method returns true if vertex has edge as an incoming edge
     * @param edge edge that is being checked for
     * @return true if edge is an incoming edge of vertex
     */
    public boolean containsInEdge(Edge edge)
    {
        return this.inEdges.contains(edge);
    }
    
    /**
     * Method returns true if vertex has edge as an outgoing edge
     * @param edge edge that is being checked for
     * @return true if edge id an outgoing edge of vertex
     */
    public boolean containsOutEdge(Edge edge)
    {
        return this.outEdges.contains(edge);
    }

    /**
     * Method removes an edge from vertex. This will delete an edge from both 
     * the outEdges and inEdges.
     * @param edge edge to be removed
     */
    public void removeEdge(Edge edge)
    {
        this.inEdges.remove(edge);
        this.outEdges.remove(edge);
    }
    
    /**
     * Method returns the vertex's number of incoming edges
     * @return 
     */
    public int getInEdgesCount()
    {
        return this.inEdges.size();
    }
    
    /**
     * Method returns the vertex's number of outgoing edges
     * @return 
     */
    public int getOutEdgesCount()
    {
        return this.outEdges.size();
    }
    
    /**
     * Method returns the vertex label
     * @return 
     */
    public String getLabel()
    {
        return this.label;
    }
    
    /**
     * Method returns all incoming edges as array list
     * @return Array List of all incoming edges
     */
    public ArrayList<Edge> getInEdges()
    {
        return new ArrayList<>(this.inEdges);
    }
    
    /**
     * Method returns all outgoing edges as array list
     * @return Array list of all outgoing edges
     */
    public ArrayList<Edge> getOutEdges()
    {
        return new ArrayList<>(this.outEdges);
    }
    
    @Override
    public boolean equals(Object other)
    {
        if(!(other instanceof Vertex))
        {
            return false;
        }
        Vertex v = (Vertex)other;
        return this.label.equals(v.label);
    }
 
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.label);
        return hash;
    }
    
    public VertexClassification getVertexClassification() {
        return this.classification;
    }
    
    public void setClassification(VertexClassification classification ) {
        this.classification = classification;
    }
    
    /**
     * Method returns direct children of this vertex
     * 
     * @return 
     */
    public ArrayList<Vertex> getChildren(){
        
        ArrayList<Vertex> children = new ArrayList<>();
        
        for(Edge e : this.outEdges){
            children.add(e.getEndVertex());
        }
        
        return children;
        
    }
}
