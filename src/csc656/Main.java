package csc656;

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


    	
        char[] alphabet = {'0', '1'};
        Graph graph = new Graph(alphabet);
        graph.buildGraph(d);
        System.out.println(graph.vertexKeys());
        System.out.println(graph.printEdges());   

	// debug //
	/* 
	for(String vStr : graph.vertexKeys()){
		Vertex v = graph.getVertex(vStr);
		System.out.println("Vertex " + vStr + " with " + v.getInEdgesCount() + " in, " + v.getOutEdgesCount() + " out");
	}
	*/
        
        // int dimensionality = 3;
        // graph.buildGraph(dimensionality);
        

	Seed seed = new Seed("001H110", 'H');
	System.out.println("seed: " + seed.toString());       
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
