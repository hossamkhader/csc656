package csc656;

public class Table3Output {
    
    private int type;
    private int in;
    private int out;
    private int [] degree;
    
    public Table3Output()
    {
        degree = new int[2];
    }

    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }

    public int getIn() {
        return in;
    }

    public void setIn(int in) {
        this.in = in;
    }

    public int getOut() {
        return out;
    }

    public void setOut(int out) {
        this.out = out;
    }
    public int[] getDegree() {
        return degree;
    }
    
    public void setDegree(int[] degree) {
        this.degree = degree;
    }
}
