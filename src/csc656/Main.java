package csc656;

public class Main
{
    public static void main(String[] args)
    {
        char[] alphabet = {'0', '1'};
        Graph graph = new Graph(alphabet);
        int dimensionality = 3;
        graph.buildGraph(dimensionality);
        
        System.out.println(graph.vertexKeys());
        System.out.println(graph.printEdges());    
    }
}
