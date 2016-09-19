package csc656;

public class Edge
{
    private final Vertex start, end;
    private final String label;
    
    public Edge(Vertex one, Vertex two, String label)
    {
        this.start = one;
        this.end = two;
        this.label = label;
    }
	     
    public Vertex getOtherVertex(Vertex current)
    {
        if(!(current.equals(start) || current.equals(end)))
        {
            return null;
        }         
        return (current.equals(start)) ? end : start;
    }
	     
    public Vertex getStartVertex()
    {
        return this.start;
    }
    
    public Vertex getEndVertex()
    {
        return this.end;
    }
    
    public String getLabel()
    {
        return this.label;
    }
}