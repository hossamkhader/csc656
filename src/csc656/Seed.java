package csc656;

/**
 * Seed object to be generated by the seed generator.
 */
public class Seed {
    //String value of the seed
    private String seedStr;

    //Length of the seed
    private int length;

    //Number of holes in the seed
    private int numHoles;

    //Character representation of the holes in the seed
    private char holeChar;

    /**
     * Constructor.
     * Given the String value of the seed and Char value of the holes,
     * calculate the number of holes and the length of the seed.
     */
    public Seed(String seed, char hole){
        seedStr = seed;
        holeChar = hole;
        length = getLength();
        numHoles = getNumHoles();
    }

    public String getSeedStr() {
        return seedStr;
    }

    public void setSeedStr(String seedStr) {
        this.seedStr = seedStr;
    }

    public int getLength() {
        return length;
    }

    /**
     * Recompute the length of the seed.
     */
    private void setLength() {
        length = seedStr.length();
    }

    public int getNumHoles() {
        return numHoles;
    }

    /**
     * Compute the number of holes in the seed.
     */
    private void setHoles() {
        //Traverse the seed, counting the number of holes encountered.
        int counter = 0;
        for(int i = 0; i < seedStr.length(); i++){
            if(seedStr.charAt(i) == holeChar){
                counter ++;
            }
        }
        numHoles = counter;
    }

    public char getHoleChar() {
        return holeChar;
    }

    @Override
    public String toString() {
        return seedStr;
    }
}