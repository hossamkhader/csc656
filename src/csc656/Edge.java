package csc656;

/**
 * Class Authors: Hossam Khader, Michael Branon
 */
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
     * Visited flag
     */
    private boolean visited;
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
        this.visited = false;
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
    
    public void setVisited()
    {
        this.visited = true;
    }
}