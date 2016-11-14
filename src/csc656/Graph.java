package csc656;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
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

    private Set<Vertex> workingSet;
    private int children10;

    private Set<String> traversals;

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
        tmp = vertices.keySet().stream().map((key) -> key + " ").reduce(tmp, String::concat);
        return tmp;

    }

    /*
  method author: Jonathon Tovey
     */
    public void removeVertex(Vertex v) {

        this.vertices.remove(v.getLabel());

        // debug //        System.out.println("Vertex " + v.getLabel() + " removed");
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

            // debug //            System.out.println("Edge " + sw + " removed");
        }

        //add seed edge
        Vertex sOrigin = graphCopy.getVertex(seed.substring(0, n - 1));
        // debug // System.out.println(sOrigin.getLabel());
        Vertex sDestination
                = this.getVertex(seed.substring(seed.length() - n + 1));
        // debug // System.out.println(sDestination.getLabel());

        // debug //
        System.out.println("Adding seed edge " + seed + " from " + sOrigin.getLabel() + " to " + sDestination.getLabel());

        graphCopy.addEdge(sOrigin, sDestination, seed);

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
                    && vertex.getVertexClassification().getDegree()[0] == 0
                    && vertex.getVertexClassification().getDegree()[1] == 1) {
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
            System.out.println(builder.toString());
            // traverses the graph until all destination vertices are linked
            while (!currentRunDestinationVerts.isEmpty() || currVertex.getOutEdgesCount() != 0) {
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
                        System.out.println(builder.toString());
                        break;
                    case 2:
                        /**
                         * necessarily a degree (2,2) vertex. When first
                         * encountered the vertex is stored in the above
                         * visitedTwoTwos map. The first edge is then traversed.
                         * When encountered again, the second edge is taken.
                         */
                        if (!visitedTwoTwos.containsValue(currVertex)) {
                            visitedTwoTwos.put(currVertex.getLabel(), currVertex);
                            builder.append(currVertex.getOutEdges().get(0).getLabel().
                                    substring(currVertex.getLabel().length(),
                                            currVertex.getOutEdges().get(0).getLabel().length()));
                            currVertex = currVertex.getOutEdges().get(0).
                                    getEndVertex();
                            System.out.println(builder.toString());
                        } else {
                            builder.append(currVertex.getOutEdges().get(1).getLabel().
                                    substring(currVertex.getLabel().length(),
                                            currVertex.getOutEdges().get(1).getLabel().length()));
                            currVertex = currVertex.getOutEdges().get(1).
                                    getEndVertex();
                            System.out.println(builder.toString());
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
                        // new edge is added between the vertices
                        String newEdgeLabel = currVertex.getLabel()
                                + bestOverlapVertex.getLabel().substring(bestOverlap);
                        //addEdge(currVertex, bestOverlapVertex, newEdgeLabel);
                        // chosen destination edge is removed from the map of 
                        // available destination vertices
                        currentRunDestinationVerts.remove(bestOverlapVertex.getLabel());
                        builder.append(bestOverlapVertex.getLabel().
                                substring(bestOverlap));
                        System.out.println(builder.toString());
                        currVertex = bestOverlapVertex;
                }
            }
            System.out.println(builder.length());
            System.out.println(builder.toString());
            if (bestRun == null) {
                bestRun = builder.toString();
            } else if (builder.toString().length() < bestRun.length()) {
                bestRun = builder.toString();
            }
        }
        System.out.println(bestRun);
        System.out.println(bestRun.length());
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

    private String getOverlap(String originLabel, String destinationLabel) {

        int labelSize = originLabel.length();

        // starting at 2 since the labels should not be completely overlapped
        // (or else they're the same)
        // and to avoid endIndex issues with j
        int i = 1;
        int j = destinationLabel.length() - 1;
        String oStr = originLabel.substring(i);
        String dStr = destinationLabel.substring(0, j);

        if (oStr.equals(dStr)) {
            return oStr;
        }

        boolean matchFound = false;
        i++;
        j--;

        // begins large and works down in order to pick biggest possible
        while (!matchFound) {

            oStr = originLabel.substring(i);
            dStr = destinationLabel.substring(0, j);

            if (oStr.equals(dStr)) {
                // debug //            
                //System.out.println("overlap " + oStr + " with " + originLabel + " and " + destinationLabel);

                return oStr;
            }
            i++;
            j--;
            if (i == labelSize) {

                // debug //
                //System.out.println("no overlap with " + originLabel + " and " + destinationLabel);
                return "";
            }
        }

        // debug //
        //System.out.println("overlap " + originLabel.substring(i) + " with " + originLabel + " and " + destinationLabel);
        return originLabel.substring(i);

    }

    private int getOverlapSize(String originLabel, String destinationLabel) {

        return getOverlap(originLabel, destinationLabel).length();
    }

    /**
     * Method reconnects a compressed, typed graph (this)
     *
     * @return a connected copy of the graph
     */
    public Graph reconnectGraph() {

        Graph graphIn = this;
        Graph graphOut = new Graph(graphIn);

        Set<Vertex> origins = new HashSet<>();
        Set<Vertex> destinations = new HashSet<>();

        // get (1, 0) and (0, 1) vertices
        for (Vertex v : graphOut.getVertices()) {
            if (v.getInEdgesCount() == 1 && v.getOutEdgesCount() == 0) {
                origins.add(v);
            } else if (v.getInEdgesCount() == 0 && v.getOutEdgesCount() == 1) {
                destinations.add(v);
            }
        }

        // debug //
        System.out.println("Origins: " + origins.size() + "; Desinations: " + destinations.size());

        Queue<Connector> pq = new PriorityQueue<>(graphOut.getNumVertices() * graphOut.getNumVertices(), connectorComparator);

        // check each origin for overlap with each destination
        for (Vertex o : origins) {
            for (Vertex d : destinations) {
                String overlap = getOverlap(o.getLabel(), d.getLabel());

                pq.add(new Connector(overlap.length(), overlap, o, d));

//                if (!overlap.equals("")) {
//                    pq.add(new Connector(overlap.length(), overlap, o, d));
//
//                    // debug //                    
//                    System.out.println("Adding to pq: " + overlap + " (" + overlap.length() + ")" + ", from " + o.getLabel() + " to " + d.getLabel());
//                } else {
//                    // debug //
//                    System.out.println("Not added to pq: " + o.getLabel() + " to " + d.getLabel());
//                }
            }
        }

        // debug //
        System.out.println("queue Size: " + pq.size());

        // reconnect
        int numReconnected = 0;
        int numToReconnect = origins.size() - 1;

        while (numReconnected < numToReconnect && pq.size() > 0) {
            Connector c = pq.poll();
            Vertex origin = c.getOrigin();
            Vertex destination = c.getDestination();

            if (areConnected(origin, destination)) {

                // debug //
//                System.out.println("skip due to connection");
                continue;
            }

            if (origin.getOutEdgesCount() == 0 && destination.getInEdgesCount() == 0) {
                Edge e = c.toEdge();
                addEdge(e.getStartVertex(), e.getEndVertex(), e.getLabel());

                numReconnected++;

                // debug //
                System.out.print("reconnected " + origin.getLabel() + " to "
                        + destination.getLabel() + " with overlap "
                        + c.getOverlap() + " (" + c.getOverlapSize() + ") ;;;;; ");
                System.out.println("total reconnected so far: " + numReconnected
                        + " and pq size " + pq.size());

            } else {

                // debug //
//                System.out.print("no reconnection: (" + origin.getInEdgesCount() + "," + origin.getOutEdgesCount()
//                        + "), (" + destination.getInEdgesCount() + "," + destination.getOutEdgesCount()
//                        + ") with overlap [" + c.getOverlap() + "] ;;;;; ");
            }

//            System.out.println("total reconnected so far: " + numReconnected
//                    + " and pq size " + pq.size());
        }

        return graphOut;

    }

    public boolean areConnected(Vertex v1, Vertex v2) {

//        // if v1 can be reached from v2
//        workingSet = new HashSet<>();
//        children10 = 0;
//        
//        addChildrenToSet(v1);
////        if (workingSet.contains(v2)) {
////            return true;
////        }
//
//        if(children10 > 1){
//            return false;
//        } else {
//            return true;
//        }
        // if v2 can be reached from v1
        workingSet = new HashSet<>();
        children10 = 0;

        addChildrenToSet(v2);
//        if (workingSet.contains(v1)) {
//            return true;
//        }

        if (children10 > 1) {
            return false;
        } else {
            return true;
        }

        // if all reachable descendents of v1 have been added to the set 
        // and v2 is not contained (and likewise with v2, v1)
        // then the nodes must not have a path between them
        // return false;
    }

    public void addChildrenToSet(Vertex v) {

        workingSet.add(v);

        if (v.getInEdgesCount() == 1 && v.getOutEdgesCount() == 0) {
            children10++;
            // debug // System.out.println(children10);
        }

        // recursive
        for (Vertex child : v.getChildren()) {
            if (!workingSet.contains(child)) {
                addChildrenToSet(child);
            }
        }
    }

    public String startTraversal(Vertex v) {

        // debug //
        System.out.println("looking at traversals from: " + v.getLabel());

        traversals = new HashSet();

        Set<Edge> edgeSet = new HashSet();
        String traversalStr = v.getLabel();
        Edge startEdge = v.getOutEdges().get(0);

        this.traverseReconnected(edgeSet, traversalStr, startEdge);

        int minLength = 99999;
        String minStr = "";

        // debug //
        System.out.println("traversals: " + traversals.size());

        for (String str : traversals) {
            if (str.length() < minLength) {
                minLength = str.length();
                minStr = str;
            }
        }

        return minStr;
    }

    public void traverseReconnected(Set<Edge> edgeSet, String currString, Edge e) {

        // debug //        System.out.println("traverseReconnected called with set size: " + edgeSet.size()                 + " (total edges: " + this.edges.size() + ")");
        // recursion control :: cancel if edge already in working set
        if (edgeSet.contains(e)) {
            return;
        } else {
            edgeSet.add(e);
        }

        Vertex destination = e.getEndVertex();
        String toAdd = "";

        if (e.getLabel().contains("H")) {
//            for (char c : e.getLabel().toCharArray()) {
//                if (c == 'H') {
//                    toAdd += c;
//                }
//            }
            int start = e.getLabel().indexOf("H");
            toAdd = e.getLabel().substring(start);

            //toAdd = e.getLabel();
            // debug //
            System.out.println("adding seed edge: " + toAdd + " for " + e.getLabel() + " (seed) from "
                    + e.getStartVertex().getLabel() + " to " + destination.getLabel());
        } else {

            if (e.getStartVertex().getLabel().contains("H")) {
                //int start = e.getLabel().lastIndexOf("") + 1;
                //toAdd = destination.getLabel().substring(start);
                toAdd = destination.getLabel();
            } else {
                // add non-overlap of destination to string
                int overlapSize = getOverlapSize(e.getStartVertex().getLabel(), destination.getLabel());
                //int overlapSize = getOverlapSize(e.getLabel(), destination.getLabel());
                toAdd = destination.getLabel().substring(overlapSize);
            }
        }

        String newString = currString + toAdd;

        if (edgeSet.size() == this.getNumEdges()) {

            // debug //            
            System.out.println("graph traversed: " + currString + "(" + currString.length() + ")");
            traversals.add(newString);

        } else {

            // recursion control :: should die at a dead end (no new edges and incomplete set)
            for (Edge ed : destination.getOutEdges()) {

                // recursion control :: shouldn't try to call again if edge has already been examined in this sequence
                if (!edgeSet.contains(ed)) {
                    Set<Edge> setCopy = new HashSet(edgeSet);
                    traverseReconnected(setCopy, newString, ed);
                }
            }
        }
    }

    public static Comparator<Connector> connectorComparator = new Comparator<Connector>() {

        @Override
        public int compare(Connector c1, Connector c2) {

            // intentionally reversed so that the queue pulls the largest rather than smallest (default)
            return (c2.getOverlapSize() - c1.getOverlapSize());
        }

    };

}
