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

        int n = 9;
        int r = 3;

        long startTime = System.nanoTime();

        Graph graph = new Graph();
        graph.buildGraph(n);

        // debug //
        System.out.println("base graph built with n = " + n);

        System.out.println("reported edges: " + graph.getNumEdges());
        Set<Edge> actualEdges = new HashSet();
        for (Vertex v : graph.getVertices()) {
            for (Edge e : v.getInEdges()) {
                actualEdges.add(e);
            }
            for (Edge e : v.getOutEdges()) {
                actualEdges.add(e);
            }
        }
        System.out.println("computed edges: " + actualEdges.size());
        System.out.println("----- ----- ----- ----- -----");

        SeedGenerator seedGen = new SeedGenerator();
        Seed seed = seedGen.generateSeed(n, r);
        // debug //
        System.out.println("seed generated: " + seed.toString());

        Graph graphCopy = graph.compressGraph(seed.toString(), n);

        // add seed edge
        graphCopy.addSeedEdge(seed.toString(), n);

        // debug //
        System.out.println("graph compressed to copy");

        System.out.println("reported edges: " + graphCopy.getNumEdges());
        actualEdges = new HashSet();
        for (Vertex v : graphCopy.getVertices()) {
            for (Edge e : v.getInEdges()) {
                actualEdges.add(e);
            }
            for (Edge e : v.getOutEdges()) {
                actualEdges.add(e);
            }
        }
        System.out.println("computed edges: " + actualEdges.size());
        System.out.println("----- ----- ----- ----- -----");

        Graph graphCopy2 = graphCopy.reconnectGraph();

        // debug //
        System.out.println("graph reconnected to copy2");

        Vertex startVertex = null;
        Vertex endVertex = null;
        int count11 = 0;
        String str11 = "";
        int count22 = 0;
        String str22 = "";

        for (Vertex v : graphCopy2.getVertices()) {
            if (v.getInEdgesCount() == 0 && v.getOutEdgesCount() == 1) {
                System.out.println("Start: " + v.getLabel() + " (" + v.getInEdgesCount() + "," + v.getOutEdgesCount() + ")");
                startVertex = v;
            } else if (v.getInEdgesCount() == 1 && v.getOutEdgesCount() == 0) {
                System.out.println("End: " + v.getLabel() + " (" + v.getInEdgesCount() + "," + v.getOutEdgesCount() + ")");
                endVertex = v;
            } else if (v.getInEdgesCount() == 1 && v.getOutEdgesCount() == 1) {
                count11++;
                str11 += " " + v.getLabel();
            } else if (v.getInEdgesCount() == 2 && v.getOutEdgesCount() == 2) {
                count22++;
                str22 += " " + v.getLabel();
            }
        }

        System.out.println("(1,1) vertices (" + count11 + "): " + str11);
        System.out.println("(2,2) vertices (" + count22 + "): " + str22);
        System.out.println("total vertices: " + graphCopy2.getNumVertices());

        System.out.println("reported edges: " + graphCopy2.getNumEdges());
        actualEdges = new HashSet();
        for (Vertex v : graphCopy2.getVertices()) {
            for (Edge e : v.getInEdges()) {
                actualEdges.add(e);
            }
            for (Edge e : v.getOutEdges()) {
                actualEdges.add(e);
            }
        }
        System.out.println("computed edges: " + actualEdges.size());
        System.out.println("----- ----- ----- ----- -----");

//        System.out.println("total edges: " + graphCopy2.getNumEdges());
//        for(Edge e : graphCopy2.getEdges()){
//            System.out.print("\t" + e.getLabel());
//        }
//        actualEdges = new HashSet();
//        Set<Edge> actualEdgesIter = new HashSet();
//        for (Vertex v : graphCopy2.getVertices()) {
//            for (Edge e : v.getInEdges()) {
//                actualEdges.add(e);
//            }
//            for (Edge e : v.getOutEdges()) {
//                actualEdges.add(e);
//            }
//        }
//        System.out.println("actual edges: " + actualEdges.size());
//
//        Set<Edge> reportedEdges = graphCopy2.getEdges();
//
//        System.out.print("edges that mismatch: ");
//        for (Edge e : actualEdges) {
//            if (!reportedEdges.contains(e)) {
//                System.out.print("\t" + e.getLabel() + " (" + e.getLabel().length() + ")");
//            }
//        }
//        System.out.println("");
//
//        for (Edge e : actualEdgesIter) {
//            //if (e.getLabel().length() < (n - 1)) {
//            if (!reportedEdges.contains(e)) {
//                System.out.println("invalid edge removed: " + e.getLabel());
//                actualEdges.remove(e);
//            }
//        }
//        System.out.println("actual edges: " + actualEdges.size());
//
//        System.out.println("");
//        for (Edge e : actualEdges) {
//            System.out.print("\t" + e.getLabel());
//        }
//        System.out.println("");
//        Set<Edge> edgeSet = new HashSet();
//        String traversalStr = startVertex.getLabel();
//        Edge startEdge = startVertex.getOutEdges().get(0);
//        
//        graphCopy2.traverseReconnected(edgeSet, traversalStr, startEdge);




        String minTraversal = graphCopy2.startTraversal(startVertex, endVertex);

        System.out.println("traversal: " + minTraversal + " (" + minTraversal.length() + ")");

	System.out.println(graphCopy2.verifyTraversals() + " traversals verified");

//	for(Edge e : actualEdges){
//		System.out.println("edge: " + e.getLabel() + " from " + e.getStartVertex().getLabel() + " to " + e.getEndVertex().getLabel());
//	}
	System.out.println("edge cout: " + actualEdges.size());

        long endTime = System.nanoTime();

        System.out.println("elapsed time: " + (endTime - startTime) + " ns or "
                + (double) ((endTime - startTime) / 1000000000.0) + " s");

    }
}
