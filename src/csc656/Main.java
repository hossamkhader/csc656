package csc656;

public class Main
{
    public static void main(String[] args)
    {
        int x = 2;
        Graph graph = new Graph();
        
        for (int i=0; i < Math.pow(2, x); i++)
        {
            String label = Integer.toBinaryString(i);
            while(label.length() < x)
            {
                label = "0" + label;
            }
            graph.addVertex(new Vertex(label));
        }
        for(String start : graph.vertexKeys())
        {
            Vertex v = graph.getVertex(start);
            for(String end : graph.vertexKeys())
            {
                if(start == end)
                {
                    break;
                }
                graph.addEdge(v, graph.getVertex(end), start + end.substring(0,1));
            }
        }
        System.out.println(graph.vertexKeys());
        System.out.println(graph.printEdges());
    }
}
