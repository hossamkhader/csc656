package csc656;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Class Authors: Hossam Khader, Michael Branon, Jonathon Tovey
 */
public class Graph {

    /**
     * List of all vertices in the graph
     */
    private final HashMap<String, Vertex> vertices;
    /**
     * List of all edges in the graph
     */
    private final HashMap<String, Edge> edges;

    /* 
     * Creates an empty graph that requires construction
     */
    public Graph() {
        this.vertices = new HashMap<>();
        this.edges = new HashMap<>();
    }

    /**
     * Copy Constructor
     *
     * @param graph
     */
    public Graph(Graph graph) {
        this.edges = new HashMap<>(graph.edges);
        this.vertices = new HashMap<>(graph.vertices);
    }

    /*
  original method author: Michael Branon
  rewrite/refactoring: Jonathon Tovey
     */
    /**
     * Constructs a graph with vertices of length d. First constructs each edge
     * and then attaches it to the appropriate vertices, constructing them if
     * necessary
     *
     * @param n length of edges/subwords
     */
    public void buildGraph(int n) {
        String[] edgeStrs = new String[(int) Math.pow((double) 2, (double) n)];
        // debug // System.out.println((int)Math.pow((double)2,(double)n));
        String frmt = "%" + n + "s";
        // debug // System.out.println(frmt);
        for (int i = 0; i < edgeStrs.length; i++) {
            edgeStrs[i] = String.format(frmt, Integer.toBinaryString(i))
                    .replace(' ', '0');
            // debug // System.out.println("creating edge " + edgeStrs[i]);

            Vertex origin;
            String originLabel = edgeStrs[i].substring(0, (edgeStrs[i].length() - 1));
            if (this.vertices.containsKey(originLabel)) {
                origin = this.vertices.get(originLabel);
            } else {
                origin = new Vertex(originLabel);
                this.addVertex(origin);
            }

            Vertex destination;
            String destLabel = edgeStrs[i].substring(1);
            if (this.vertices.containsKey(destLabel)) {
                destination = this.vertices.get(destLabel);
            } else {
                destination = new Vertex(destLabel);
                this.addVertex(destination);
            }

            addEdge(origin, destination, edgeStrs[i]);
            /// debug // System.out.println("adding edge " + edgeStrs[i]);	
        }
    }

    /**
     * Method checks for existence of an edge in the graph
     *
     * @param e the desired edge
     * @return true if edge is found
     */
    public boolean containsEdge(Edge e) {
        if (e.getStartVertex() == null || e.getEndVertex() == null) {
            return false;
        }
        return this.edges.containsKey(e.getLabel());
    }

    /**
     * Method removes an edge from the graph, along with the references to it
     * from its origin and destination vertices
     *
     * @param e the edge to be removed
     * @return the removed edge if it was removed successfully or null
     */
    public Edge removeEdge(Edge e) {
        e.getStartVertex().removeEdge(e);
        e.getEndVertex().removeEdge(e);
        return this.edges.remove(e.getLabel());
    }

    /**
     * Method adds an edge to the graph
     *
     * @param start vertex of origin
     * @param end destination vertex
     * @param label name of the edge
     */
    public void addEdge(Vertex start, Vertex end, String label) {
        Edge e = new Edge(start, end, label);
        edges.put(label, e);
        start.addOutEdge(e);
        end.addInEdge(e);
    }

    /**
     * Method returns keys of edges in graph
     *
     * @return set of edges
     */
    public Set<Edge> getEdges() {
        return new HashSet<>(this.edges.values());
    }

    /**
     * Method returns the number of edges contained in the graph
     *
     * @return number of edges
     */
    public int getNumEdges() {
        return this.edges.size();
    }

    /**
     * Method checks for the existence of a vertex in the graph
     *
     * @param vertex
     * @return
     */
    public boolean containsVertex(Vertex vertex) {
        return this.vertices.get(vertex.getLabel()) != null;
    }

    /**
     * Method returns a vertex
     *
     * @param label String label of the desired vertex
     * @return the desired vertex or null if no mapping was found
     */
    public Vertex getVertex(String label) {
        return vertices.get(label);
    }

    /**
     * Method adds a vertex to the graph
     *
     * @param vertex
     */
    public void addVertex(Vertex vertex) {
        vertices.put(vertex.getLabel(), vertex);
    }

    /**
     * Method returns keys of vertices in graph
     *
     * @return set of keys for vertex map
     */
    public Set<String> vertexKeys() {
        return this.vertices.keySet();
    }

    /**
     * Method returns the number of vertices contained in the graph
     *
     * @return number of vertices
     */
    public int getNumVertices() {
        return this.vertices.size();
    }

    /**
     * Method creates a string representation of the graph
     *
     * @return String of the graph
     */
    public String printEdges() {
        /**
         * TODO: Maybe change the output to something more readable? (edges in
         * order?)
         */
        String tmp = "";
        for (String key : edges.keySet()) {
            tmp += edges.get(key).getLabel();
            tmp += " : ";
            tmp += edges.get(key).getStartVertex().getLabel();
            tmp += " --> ";
            tmp += edges.get(key).getEndVertex().getLabel();
            tmp += "\n";
        }
        return tmp;
    }

    /*
  method author: Jonathon Tovey
     */
    public String printVertices() {

        String tmp = "";
        for (String key : vertices.keySet()) {
            tmp += vertices.get(key).getLabel();
        }
        return tmp;
    }

    /*
  method author: Jonathon Tovey
     */
    public void removeVertex(Vertex v) {

        this.vertices.remove(v.getLabel());

        // debug //
        System.out.println("Vertex " + v.getLabel() + " removed");
    }

    /*
  method author: Jonathon Tovey
     */
    /**
     * Method compresses a graph (this)
     *
     * @param seed
     * @param n
     * @return a compressed copy of the graph
     */
    public Graph compressGraph(String seed, int n) {

        Graph graphCopy = new Graph(this);

        String[] subwords = SeedToSubwords.swOfLength(seed, n);

        for (String sw : subwords) {

            graphCopy.removeEdge(edges.get(sw));

            // debug //
            System.out.println("Edge " + sw + " removed");
        }

        // remove any (0, 0) vertices
        Graph graphCopyIter = new Graph(graphCopy);
        for (String vStr : graphCopyIter.vertexKeys()) {

            Vertex v = graphCopy.getVertex(vStr);

            // debug // System.out.println("Vertex " + vStr + " with " + v.getInEdgesCount() + " in, " + v.getOutEdgesCount() + " out");
            // if vertex has zero edges, remove
            if (v.getInEdgesCount() == 0 && v.getOutEdgesCount() == 0) {
                graphCopy.removeVertex(v);
            }
            // debug // System.out.println(graphCopy.printVertices());
        }
        return graphCopy;
    }

    /**
     * Method adds seed edge to graph (this)
     * @param seed
     * @param n
     */
    public void addSeedEdge(String seed, int n) {
        Vertex sOrigin = this.getVertex(seed.substring(0, n - 1));
        // debug // System.out.println(sOrigin.getLabel());
        Vertex sDestination = this.getVertex(seed.substring(seed.length() - n + 1));
        // debug // System.out.println(sDestination.getLabel());

        this.addEdge(sOrigin, sDestination, seed);

        // debug //
        System.out.println("Added seed edge " + seed + " from " + sOrigin.getLabel() + " to " + sDestination.getLabel());
    }

    /**
     * Method returns an array of the vertices contained in the graph
     *
     * @return array of vertices
     */
    public Vertex[] getVertices() {
        Vertex[] temp = new Vertex[this.vertices.values().size()];
        this.vertices.values().toArray(temp);
        return temp;
    }
	
/**
     * Stitches together the disjointed graph;
     */
    public void stitch() {
        //inizialize a hashmap with all type 1 vertices
        HashMap<String, Vertex> tOneVerts = new HashMap<>();
        for (Vertex vertex : this.getVertices()) {
            if (vertex.getVertexClassification().getType().length > 0
                    && vertex.getVertexClassification().getType()[0] == 1) {
                tOneVerts.put(vertex.getLabel(), vertex);
            }
        }
        // intialize a hasmap with all degree (0,1) vertices
        HashMap<String, Vertex> destinationVerts = new HashMap<>();
        for (Vertex vertex : this.getVertices()) {
            if (vertex.getVertexClassification().getDegree().length > 0
                    && vertex.getInEdgesCount() == 0
                    && vertex.getOutEdgesCount() == 1) {
                destinationVerts.put(vertex.getLabel(), vertex);
            }
        }
        /*
        // get a "random" source vertex of degree (0,1) and remove it from 
        // destination map
        Vertex currVertex = destinationVerts.
                get(destinationVerts.keySet().iterator().next());
        destinationVerts.remove(currVertex.getLabel());
         */
        String bestRun = null;
        for (String label : destinationVerts.keySet()) {
            StringBuilder builder = new StringBuilder();
            // create a copy of destination vertices from master
            HashMap<String, Vertex> currentRunDestinationVerts
                    = new HashMap<>(destinationVerts);

            // set source vertex and remove it from the cloned destination map
            Vertex currVertex = currentRunDestinationVerts.get(label);
            currentRunDestinationVerts.remove(currVertex.getLabel());

            // add source to string builder
            builder.append(currVertex.getLabel());

            // Keeps track of visited (2,2) vertices
            HashMap<String, Vertex> visitedTwoTwos = new HashMap<>();
            // traverses the graph until all destination vertices are linked
            while (!currentRunDestinationVerts.isEmpty()||currVertex.getOutEdgesCount()!=0) {
                switch (currVertex.getOutEdgesCount()) {
                    case 1:
                        /**
                         * after the initial source vertex, necessarily a degree
                         * (1,1) vertex
                         */
                        builder.append(currVertex.getOutEdges().get(0).getLabel().
                                substring(currVertex.getLabel().length(),
                                        currVertex.getOutEdges().get(0).getLabel().length()));
                        currVertex = currVertex.getOutEdges().get(0).getEndVertex();
                        break;
                    case 2:
                        /**
                         * necessarily a degree (2,2) vertex. When first
                         * encountered the vertex is stored in the above
                         * visitedTwoTwos map. The first edge is then traversed.
                         * When encountered again, the second edge is taken.
                         */
                        if (!visitedTwoTwos.containsKey(currVertex.getLabel())) {                     
                            visitedTwoTwos.put(currVertex.getLabel(), currVertex);
                            builder.append(currVertex.getOutEdges().get(0).getLabel().
                                substring(currVertex.getLabel().length(),
                                        currVertex.getOutEdges().get(0).getLabel().length()));
                            currVertex = currVertex.getOutEdges().get(0).
                                    getEndVertex();
                        } else {
                            builder.append(currVertex.getOutEdges().get(1).getLabel().
                                substring(currVertex.getLabel().length(),
                                        currVertex.getOutEdges().get(1).getLabel().length()));
                            currVertex = currVertex.getOutEdges().get(1).
                                    getEndVertex();
                        }
                        break;
                    default:
                        /**
                         * Necessarily a type 1 vertex of degree (1,0).
                         */
                        Vertex bestOverlapVertex = null;
                        int bestOverlap = 0;
                        int tempOverlap = 0;
                        // all destination vertices are compared to maximize overlap
                        for (String availableLabel : currentRunDestinationVerts.keySet()) {
                            if (bestOverlapVertex == null) {
                                bestOverlapVertex = currentRunDestinationVerts.get(availableLabel);
                                bestOverlap
                                        = computeOverlap(currVertex.getLabel(),
                                                availableLabel);
                            } else {
                                tempOverlap = computeOverlap(currVertex.getLabel(),
                                        availableLabel);
                                if (tempOverlap > bestOverlap) {
                                    bestOverlap = tempOverlap;
                                    bestOverlapVertex = currentRunDestinationVerts.get(availableLabel);
                                }
                            }
                        }
                        // chosen destination edge is removed from the map of 
                        // available destination vertices
                        currentRunDestinationVerts.remove(bestOverlapVertex.getLabel());
                        builder.append(bestOverlapVertex.getLabel().
                                substring(bestOverlap));
                        currVertex = bestOverlapVertex;
                }
            }
            if(bestRun==null){
                bestRun=builder.toString();
            }else if(builder.toString().length()<bestRun.length()){
                bestRun=builder.toString();
            }
        }
        System.out.println(bestRun);
        System.out.println(bestRun.length());
        System.out.println(getComplete(bestRun));
    }

    private boolean getComplete(String run) {
        boolean exists=false;
        for (String edgeLabel : this.edges.keySet()) {
            int offset = 0;
            exists=false;
            while (offset <= run.length() - edgeLabel.length()) {
                if (run.startsWith(edgeLabel, offset)) {
                    exists=true;
                    offset=run.length();
                } else {
                    offset++;
                }
            }
            if(!exists){
                System.out.println(edgeLabel);
                return false;
            }
        }
        return true;
    }
    /**
     * Computes the overlap between 2 strings
     *
     * @param uLabel
     * @param vLabel
     * @return
     */
    private int computeOverlap(String uLabel, String vLabel) {
        int overlap;
        while (!uLabel.equalsIgnoreCase(vLabel)) {
            uLabel = uLabel.substring(1);
            vLabel = vLabel.substring(0, vLabel.length() - 1);
        }
        overlap = uLabel.length();
        return overlap;
    }

public String getVertexTypeCount() {
        int type1 = 0;
        int type2 = 0;
        int type3 = 0;
        int type4 = 0;
        int type5 = 0;
        int type6 = 0;
        int type7 = 0;
        int type26 = 0;
        int type27 = 0;
        int type37 = 0;
        String result = "";

        for (String label : vertices.keySet()) {
            Vertex vertex = vertices.get(label);
            if (vertex.getVertexClassification().getType().length == 1) {
                switch (vertex.getVertexClassification().getType()[0]) {
                    case 1:
                        type1++;
                        break;
                    case 2:
                        type2++;
                        break;
                    case 3:
                        type3++;
                        break;
                    case 4:
                        type4++;
                        break;
                    case 5:
                        type5++;
                        break;
                    case 6:
                        type6++;
                        break;
                    case 7:
                        type7++;
                        break;
                }
            }
            if (vertex.getVertexClassification().getType().length == 2) {
                if (vertex.getVertexClassification().getType()[0] == 2
                        && vertex.getVertexClassification().getType()[1] == 6) {
                    type26++;
                }
                if (vertex.getVertexClassification().getType()[0] == 2
                        && vertex.getVertexClassification().getType()[1] == 7) {
                    type27++;
                }
                if (vertex.getVertexClassification().getType()[0] == 3
                        && vertex.getVertexClassification().getType()[1] == 7) {
                    type37++;
                }
            }
        }
        result += "********************";
        result += "\n";
        result += "Type[1]:" + type1;
        result += "\n";
        result += "Type[2]:" + type2;
        result += "\n";
        result += "Type[2,6]:" + type26;
        result += "\n";
        result += "Type[2,7]:" + type27;
        result += "\n";
        result += "Type[3]:" + type3;
        result += "\n";
        result += "Type[3,7]:" + type37;
        result += "\n";
        result += "Type[4]:" + type4;
        result += "\n";
        result += "Type[5]:" + type5;
        result += "\n";
        result += "Type[6]:" + type6;
        result += "\n";
        result += "Type[7]:" + type7;
        result += "\n";
        result += "********************";
        result += "\n";

        return result;
    }
}
