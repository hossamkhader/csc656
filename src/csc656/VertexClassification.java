package csc656;

import java.util.ArrayList;

public class VertexClassification {
    
    private final ArrayList<Integer> type;
    private int inBit = -1; //no bit set
    private int outBit = -1;
    private int [] degree;
    
    public VertexClassification()
    {
        degree = new int[2];
        type = new ArrayList<>();
    }

    public int [] getType() {
        int [] temp = new int[type.size()];
        for(int i = 0 ; i < type.size() ; i ++)
        {
            temp[i] = type.get(i);
        }
        return temp;
    }
    
    public void addType(int type) {
        this.type.add(type);
    }

    public int getInBit() {
        return inBit;
    }

    public void setInBit(int inBit) {
        this.inBit = inBit;
    }

    public int getOutBit() {
        return outBit;
    }

    public void setOutBit(int outBit) {
        this.outBit = outBit;
    }
    public int[] getDegree() {
        return degree;
    }
    
    public void setDegree(int[] degree) {
        this.degree = degree;
    }
}
