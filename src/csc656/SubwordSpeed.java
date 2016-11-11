/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc656;

/**
 *
 * @author yevot
 */
public class SubwordSpeed {

    public static void main(String[] args) {
        int n = 12;
        int r = 6;
        
        String result = "";
        /*
        while(n < 23){
            result += Test(n, r);
            n += 2;
            r += 2;
        }
        */
        result += Test(16, 6);
        
        System.out.println(result);
        
    }
    
    
    private static String Test(int n, int r){
        
        //int h = n - r;
        
        String result = "";
        
        long start = System.nanoTime();
        
        Graph graph = new Graph();
        graph.buildGraph(n);
        
        SeedGenerator seedgen = new SeedGenerator();
        Seed seed = seedgen.generateSeed(n, r);
        
        Graph graphCopy = graph.compressGraph(seed.toString(), n);
        
        long end = System.nanoTime();
        
        result = "\nElapsed Time for n=" + n + ", r=" + r + " with seed " 
                    + seed.getSeedStr() + ": \t" + (end-start) 
                    + " ns or \t" + ((double)end-start)/1000000000 + " s";
        
        return result;
        
    }
    
        

}
