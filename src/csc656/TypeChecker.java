package csc656;

/**
 * Class authors: Michael Branon, Hossam Khader, Kenneth Short, Jonathon Tovey.
 */
public class TypeChecker {
    private final int n;
    private final int r; //n - h
    private String label;

    public TypeChecker(int n, int r){
        this.n = n;
        this.r = r;
    }

    public void setType(Vertex vertex){
        this.label = vertex.getLabel();
        VertexClassification classification = new VertexClassification();

        if(checkType1()){
            classification.addType(1);
        }if(checkType2()){
            classification.addType(2);
        }if(checkType3()){
            classification.addType(3);
        }if(checkType4()){
            classification.addType(4);
        }if(checkType5()){
            classification.addType(5);
        }if(checkType6()){
            classification.addType(6);
        }if(checkType7()){
            classification.addType(7);
        }

        vertex.setClassification(classification);
    }

    /**
     * Method author: Michael Branon
     */
    private boolean checkType1() {
        int i, j;
        i = 0;

        for (j = 0; j < label.length(); j++) {
            if (label.charAt(j) == '0') {
                i++;
            } else {
                break;
            }
        }
        if (!(i >= r - 1 && i <= n - 3)) {
            return false;
        }
        return label.charAt(j) == '1' && label.substring(j + 1).length() == n - i - 2;
    }

    /**
     * Method author: Michael Branon
     */
    private boolean checkType2() {
        int i, j;
        i = 0;

        for (j = 0; j < label.length(); j++) {
            if (label.charAt(j) == '0') {
                i++;
            } else {
                break;
            }
        }
        if (!(i == r - 2)) {
            return false;
        }
        return label.charAt(j) == '1' && label.substring(j + 1).length() == n - r;
    }

    /**
     * Method author: Michael Branon
     * 
     * Method checks if vertex is of type 3 as per:
     * 0^(i) 1 @^(n-r) 0^(r-i-2) for some i in [0..r-3]
     * @return 
     */
    private boolean checkType3() {
        // the variable m is used to count suffix 0's
        int i, j, m;
        m = 0;
        i = 0;
        
        //get count of 0's and see if within range, also check for following 1 
        for (j = 0; j < label.length(); j++) {
            if (label.charAt(j) == '0') {
                i++;
            } else {
                break;
            }
        }
        if (!(i >= 0 && i <= r - 3 && this.label.charAt(j)=='1')) {
            return false;
        }
        
        //checks that vertex has enough chars to acomodate remaining operations
        if (n - r < label.length() - (j + 1)) {
            //gets count of 0's following @'s and verifies
            for (int k = j + (n - r) + 1; k < label.length(); k++) {
                if (label.charAt(k) != '0') {
                    return false;
                } else {
                    m++;
                }
            }
            return m == (r - i - 2);
        }
        return false;
    }
    
    /**
     *  Method author: Michael Branon
     * 
     * Method checks if vertex is of type 4 as per:
     * @^(n-r) 0^(r-2) 1
     * @return 
     */
    private boolean checkType4() {
        int j;

        //begins counting 0's after @'s, verifying their number
        if(this.label.charAt(this.label.length()-1)!='1'){
            return false;
        }
        for(j=this.label.length()-2; j>n-r-1; j--){
            if(this.label.charAt(j)!='0'){
                return false;
            }
        }
        return j==n-r-1;
    }

    /**
     *  Method author: Michael Branon
     * 
     * Method checks if vertex is of type 5 as per:
     * @^(n-r-i+1) 0^(r-2) 1^i for some i in [2..(n-r-2)/2] 
     * unlike similar methods, this one is checked in reverse to more easily 
     * accommodate the suffix
     * @return 
     */
    private boolean checkType5() {
        //variable m is used to track the number of 0's
        int j, i, m;
        i = 0;

        //gets count of 1's in suffix and verifies within range 
        for (j = label.length() - 1; j >= 0; j--) {
            if (label.charAt(j) == '1') {
                i++;
            } else {
                break;
            }
        }
        if (!(i >= 2 && i <= ((n - r - 2) / 2))) {
            return false;
        }

        //verifies the correct number of 0's, and the correct number of @'s
        for (m=j; m>j-(r-2); m--) {
            if (label.charAt(m) != '0') {
                return false;
            }
        }
        return m+1==(n-r-i+1); 
    }

/*
  method author: Jonathon Tovey
*/

    private boolean checkType6(){
        int lastChar = label.length() - 1;

        // check 1s at end
        for(int i = 0; i < (int)((n - r) / 2); i++){
            if(label.charAt(lastChar) != '1'){
                return false;
            } else {
                lastChar--;
            }
        }

        // check 0s
        for(int i = 0; i < (r - 2); i++){
            if(label.charAt(lastChar) != '0'){
                return false;
            } else {
                lastChar--;
            }
        }


        // check remaining length
        if((lastChar + 1) == ((n - r + 2) / 2)){
            return true;
        }

        return false;
    }

    /**
     * Method author:  Kenneth Short
     *
     * Check to see if a type 7 node using equation:
     * 'hole'^[(n - r - 2i + 2)/2] 0^(r - 2) 1^[(n - r)/2] 0^i
     * for some i in [1...(n - r)/2].
     */
    private boolean checkType7(){
        int num1 = (n - r)/2;
        int numMid0 = (r - 2);
        int iUpper = (n - r)/2; //upper limit on i value
        int currIndex = label.length() - 1; //start at end of the label

        //Verify number of 0's at end of string
        //Must be i number of 0'
        int zeroCount = 0;
        for(int i = currIndex; i >= 0; i--){
            if(label.charAt(i) == '0') { //get string of zeroes
                zeroCount++;
                currIndex --;
            }
            else
                i = -1; //break out of loop
        }

        boolean isValid = false;
        for(int i = 1; i <= iUpper && !isValid; i++){
            if(zeroCount == i)
                isValid = true;
        }

        if(!isValid)
            return false;

        //Verifiy number of 1's before 0's at end
        //Must be (n - r)/2
        int oneCount = 0;
        for(int i = currIndex; i >= 0; i--){
            if(label.charAt(i) == '1'){
                oneCount ++;
                currIndex --;
            }
            else
                i = -1; //break out of loop
        }

        if(oneCount != num1)
            return false;

        //Verify the number of 0's in middle of string
        //Must be (r - 2)
        int midZeroCount = 0;
        for(int i = currIndex; i >= 0; i--){
            if(label.charAt(i) == '0'){
                midZeroCount ++;
                currIndex --;

                //Rest of string is 'holes'
                if(midZeroCount == numMid0){
                    //Verify number of any characters (holes) at beginning of string
                    //Must be (n - r - 2i + 2)/2
                    for(int j = 1; i <= iUpper; j++){
                        if((currIndex + 1) == (n - r - (2*j) + 2)/2)
                            return true;
                    }
                }
            }
            else
                i = -1; //break out of loop
        }
        return false;
    }
}
