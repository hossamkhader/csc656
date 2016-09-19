package csc656;

public class Edge
{
    /**
     * Origin and destination vertices of the edge
     */
    private final Vertex start, end;
    /**
     * Edge label
     */
    private final String label;
    
    /**
     * Creates an edge
     * @param one origin vertex
     * @param two destination vertex
     * @param label edge label
     */
    public Edge(Vertex one, Vertex two, String label)
    {
        this.start = one;
        this.end = two;
        this.label = label;
    }
	
    /**
     * Method returns origin vertex
     * @return 
     */
    public Vertex getStartVertex()
    {
        return this.start;
    }
    
    /**
     * Method returns destination vertex
     * @return 
     */
    public Vertex getEndVertex()
    {
        return this.end;
    }
    
    /**
     * Method return label of edge
     * @return 
     */
    public String getLabel()
    {
        return this.label;
    }
}