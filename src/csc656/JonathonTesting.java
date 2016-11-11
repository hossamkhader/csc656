/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc656;

import java.util.Arrays;

/**
 *
 * @author Jonathon Tovey
 */
public class JonathonTesting {

    public static void main(String args[]) {

        int n = 10;
        int r = 4;

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

        for (Vertex v : graphCopy2.getVertices()) {
            if (v.getInEdgesCount() == 1 && v.getOutEdgesCount() == 0) {
                System.out.println("(1,0): " + v.getLabel());
            } else if (v.getInEdgesCount() == 0 && v.getOutEdgesCount() == 1) {
                System.out.println("(0,1): " + v.getLabel());
            }
        }

        // debug //
        System.out.println("graph reconnected to copy");

        
        long endTime = System.nanoTime();

        System.out.println("elapsed time: " + (endTime - startTime) + " ns or " 
                + (double)((endTime - startTime)/1000000000.0) + " s");

    }
}
