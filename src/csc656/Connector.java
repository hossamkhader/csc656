/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc656;

/**
 *
 * @author Jonathon Tovey
 */
public class Connector {
    
    private int overlapSize;
    private String overlapString;
    private Vertex origin;
    private Vertex destination;
    
    public Connector(){
        
    }
    
    public Connector(int overlapSize, String overlapString, Vertex origin, Vertex destination){
        this.overlapSize = overlapSize;
        this.overlapString = overlapString;
        this.origin = origin;
        this.destination = destination;
    }
    
    public Edge toEdge(){
        
        return new Edge(origin, destination, (origin.getLabel().substring(0, overlapSize) + destination.getLabel()));
        
    }
    
    public String getOverlap(){
        return this.overlapString;
    }
    
    public int getOverlapSize(){
        return this.overlapSize;
    }
    
    public Vertex getOrigin(){
        return this.origin;
    }
   
    public Vertex getDestination(){
        return this.destination;
    }
}
