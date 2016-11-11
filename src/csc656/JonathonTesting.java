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

        int n = 12;
        int r = 6;

        long startTime = System.currentTimeMillis();

        String result;

        Graph graph = new Graph();
        graph.buildGraph(n);

        // debug //
        System.out.println("base graph built");

//        result = "";
//        result += "Vertices:";
//        result += "\n";
//        result += graph.vertexKeys();
//        result += "\n";
//        result += "\n";
//        result += "Edges:";
//        result += "\n";
//        result += graph.printEdges();
//        result += "\n";
//        Seed seed;
        SeedGenerator seedGen = new SeedGenerator();
        Seed seed = seedGen.generateSeed(n, r);

        // debug //
        System.out.println(seed.toString());

//        result += ("Seed: " + seed);
//        result += "\n";
//        result += "\n";
        Graph graphCopy = graph.compressGraph(seed.toString(), n);

        // debug //
        System.out.println("graph compressed to copy");

//        result += ("Vertices:");
//        result += "\n";
//        result += graphCopy.vertexKeys();
//        result += "\n";
//        result += "\n";
//        result += "Edges:";
//        result += "\n";
//        result += graphCopy.printEdges();
//        result += "\n";
//        result += "\n";
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

//        result += "Vertices with Types:";
//        result += "\n";        
//        TypeChecker tCheck = new TypeChecker(n, r);
//        Table3 table3 = new Table3(n, r);
//        for (Vertex vertex : graphCopy.getVertices()) {
//            tCheck.setType(vertex);
//            if (vertex.getVertexClassification().getType().length >= 1) {
//                result += (vertex.getLabel() + ": Type=["
//                        + vertex.getVertexClassification().getType()[0]);
//                if (vertex.getVertexClassification().getType().length > 1) {
//                    result += ",";
//                    result += vertex.getVertexClassification().getType()[1];
//                }
//                result += "] ";
//
//                try {
//                    table3.checkType(vertex);
//                    result += ", degree: " + Arrays.toString(
//                            vertex.getVertexClassification().getDegree()
//                    );
//                } catch (SubTypeNotFound | DegreeMismatch e) {
//                    result += e.getMessage();
//                }
//
//                result += "\n";
//            }
//        }
        long endTime = System.currentTimeMillis();
//        System.out.println(result);
        System.out.println("Execution Time: " + (endTime - startTime) + " milliseconds");

    }
}
