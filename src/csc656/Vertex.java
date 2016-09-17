package csc656;

import java.util.ArrayList;
import java.util.Objects;

public class Vertex
{
    private final ArrayList<Edge> in_edges;
    private final ArrayList<Edge> out_edges;
    private final String label;
	     
    public Vertex(String label)
    {
        this.label = label;
        this.in_edges = new ArrayList<>();
        this.out_edges = new ArrayList<>();
    }
    
    public void addEdge(Edge edge)
    {
        if(this.in_edges.contains(edge) || this.out_edges.contains(edge))
        {
            return;
        }
        if(edge.getStartVertex()== this)
        {
            this.out_edges.add(edge);
        }
        else if(edge.getEndVertex()== this)
        {
            this.in_edges.add(edge);
        }
    }
    
    public boolean containsEdge(Edge edge)
    {
        return this.in_edges.contains(edge) || this.in_edges.contains(edge);
    }
    
    public boolean containsInEdge(Edge edge)
    {
        return this.in_edges.contains(edge);
    }
    
    public boolean containsOutEdge(Edge edge)
    {
        return this.in_edges.contains(edge);
    }

    public void removeEdge(Edge edge)
    {
        this.in_edges.remove(edge);
        this.out_edges.remove(edge);
    }
    
    public int getInEdgesCount()
    {
        return this.in_edges.size();
    }
    
    public int getOutEdgesCount()
    {
        return this.out_edges.size();
    }
    
    public String getLabel()
    {
        return this.label;
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
    
    public ArrayList<Edge> getInEdges()
    {
        return new ArrayList<>(this.in_edges);
    }
    
    public ArrayList<Edge> getOutEdges()
    {
        return new ArrayList<>(this.out_edges);
    }
}
