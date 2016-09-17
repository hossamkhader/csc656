package csc656;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Graph
{
    private final HashMap<String, Vertex> vertices;
    private final HashMap<Integer, Edge> edges;
    
    public Graph()
    {
        this.vertices = new HashMap<>();
        this.edges = new HashMap<>();
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
        edges.put(e.hashCode(), e);
        start.addEdge(e);
        end.addEdge(e);
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
        for(int key : edges.keySet())
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