package csc656;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
    	// command line from /src/: java csc656.Main d h
    	// d and h as ints
    	// must be true: d > (h + 1)
    	// d is dimentionality (length of vertices)
    	// h is number of holes
    	
        int d = 0, h = 0, n = 0;
    	if(args.length != 0){
        	try {
        		d = Integer.parseInt(args[0]);
        		h = Integer.parseInt(args[1]);
        	} catch (Exception e) {
        		System.out.println(e + "/nUsing default values...");
        		d = 3;
        		h = 1;
        	}
        } else {
        	d = 3;
        	h = 1;
        }
    	
    	n = d + 1;
    	System.out.println("d: " + d + ", h: " + h + ", n: " + n);

        Graph graph = new Graph();
        graph.buildGraph(d);
        System.out.println(graph.vertexKeys());
        System.out.println(graph.printEdges());   

	
	for(String vStr : graph.vertexKeys()){
		Vertex v = graph.getVertex(vStr);
                ArrayList<String> inEdges = new ArrayList();
                ArrayList<String> outEdges = new ArrayList();
                for(Edge e : v.getInEdges()){
                    inEdges.add(e.getLabel());
                }
                for(Edge e : v.getOutEdges()){
                    outEdges.add(e.getLabel());
                }
		System.out.println("Vertex " + vStr + " with:\n" 
                        + v.getInEdgesCount() + " in: " + inEdges.toString() 
                        + "\n" + v.getOutEdgesCount() +  " out: " 
                        + outEdges.toString());
	}
	
	Seed seed = new Seed("001H110", 'H');
	System.out.println("\nseed: " + seed.toString());       
        Graph graphCopy = graph.compressGraph(seed.toString(), n);
        System.out.println(graphCopy.vertexKeys());
        System.out.println(graphCopy.printEdges());  

        SeedGenerator seedGen = new SeedGenerator();
        seed = seedGen.generateSeed(n, h);
	System.out.println("seed: " + seed);       
        Graph graphCopy2 = graph.compressGraph(seed.toString(), n);
        System.out.println(graphCopy2.vertexKeys());
        System.out.println(graphCopy2.printEdges());  

    }
}
