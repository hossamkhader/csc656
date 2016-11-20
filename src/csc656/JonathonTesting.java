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

        int n = 10;
        int r = 4;

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


        String minTraversal = graphCopy2.startTraversal(startVertex, endVertex);

        System.out.println("traversal: " + minTraversal + " (" + minTraversal.length() + ")");

        long endTime = System.nanoTime();

        System.out.println("elapsed time: " + (endTime - startTime) + " ns or "
                + (double) ((endTime - startTime) / 1000000000.0) + " s");

    }
}
