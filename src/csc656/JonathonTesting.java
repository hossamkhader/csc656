/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc656;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Jonathon Tovey
 */
public class JonathonTesting {

    public static void main(String args[]) {

        int n = 7;
        int r = 3;

        long startTime = System.nanoTime();

        String result;

        Graph graph = new Graph();
        graph.buildGraph(n);

        // debug //
        System.out.println("base graph built with n = " + n);

        SeedGenerator seedGen = new SeedGenerator();
        Seed seed = seedGen.generateSeed(n, r);

        // debug //
        System.out.println("seed: " + seed.toString());

        Graph graphCopy = graph.compressGraph(seed.toString(), n);

        // debug //
        System.out.println("graph compressed to copy");

        Graph graphCopy2 = graphCopy.reconnectGraph();

        Vertex startVertex = null;
        
        for (Vertex v : graphCopy2.getVertices()) {
            if (v.getInEdgesCount() == 1 && v.getOutEdgesCount() == 0) {
                System.out.println("(1,0): " + v.getLabel());
            } else if (v.getInEdgesCount() == 0 && v.getOutEdgesCount() == 1) {
                System.out.println("(0,1): " + v.getLabel());
                startVertex = v;
            }
        }

        // debug //
        System.out.println("graph reconnected to copy");
        
//        Set<Edge> edgeSet = new HashSet();
//        String traversalStr = startVertex.getLabel();
//        Edge startEdge = startVertex.getOutEdges().get(0);
//        
//        graphCopy2.traverseReconnected(edgeSet, traversalStr, startEdge);

        String minTraversal = graphCopy2.startTraversal(startVertex);
        
        System.out.println("traversal: " + minTraversal + " (" + minTraversal.length() + ")");

        
        long endTime = System.nanoTime();

        System.out.println("elapsed time: " + (endTime - startTime) + " ns or " 
                + (double)((endTime - startTime)/1000000000.0) + " s");

    }
}
