package csc656;

public class Table3 {
    
    private final int n;
    private final int r; //n - h
    private final String label;
    private final Vertex vertex;
    
    public Table3(Vertex vertex, int numHoles)
    {
        this.vertex = vertex;
        this.label = vertex.getLabel();
        n = label.length() + 1;
        r = n - numHoles;
    }
    
    
    public void checkType(int [] type)
    {
        if(checkType1(type) != null)
        {
            vertex.setClassification(checkType1(type));
        }
        
        if(checkType2(type) != null)
        {
            vertex.setClassification(checkType2(type));
        }
        vertex.setClassification(null);
    }
    
    private VertexClassification checkType1(int [] type)
    {
        if(type[0] != 1 && type.length != 1)
        {
            return null;
        }
        if(checkType1_1() != null)
            return checkType1_1();
        if(checkType1_2() != null)
            return checkType1_2();
        if(checkType1_3() != null)
            return checkType1_3();
        if(checkType1_4() != null)
            return checkType1_4();
        return null;
    }
    
    private VertexClassification checkType2(int [] type)
    {
        if(type[0] != 2 || type[1] != 2)
        {
            return null;
        }
        if(checkType2_1() != null)
            return checkType2_1();
        if(checkType2_2() != null)
            return checkType2_2();
        if(checkType2_3() != null)
            return checkType2_3();
        if(checkType2_4() != null)
            return checkType2_4();
        if(checkType2_5() != null)
            return checkType2_5();
        if(checkType2_6() != null)
            return checkType2_6();
        return null;
    }
    
    private VertexClassification checkType1_1()
    {
        int i, j;
        i = 0;

        for (j = 0; j < label.length(); j++) {
            if (label.charAt(j) == '0') {
                i++;
            } else {
                break;
            }
        }
        if (!(i >= (n + r - 2)/2 && i <= n - 3)) {
            return null;
        }
        if(label.charAt(i+1) == '1' && label.charAt(label.length() - 1) == '0')
        {
            VertexClassification result = new VertexClassification();
            result.setInBit(1);
            result.addType(1);
            int [] degree = {1,0};
            result.setDegree(degree);
            return result;
        }
        return null;
    }
    
    
    private VertexClassification checkType1_2()
    {
        int i, j;
        i = 0;

        for (j = 0; j < label.length(); j++) {
            if (label.charAt(j) == '0') {
                i++;
            } else {
                break;
            }
        }
        if (!(i >= (n + r - 2)/2 && i <= n - 3)) {
            return null;
        }
        if(label.charAt(i+1) == '1' && 
           label.charAt(label.length() - 1) == '1' && 
           label.substring(i+2, label.length() -2) != Utils.power("1", n - i - 3))
        {
            for (int k = 0 ; k <= n-r-i-1 ; k++)
            {
                if(Utils.suffex(label.substring(i+1, label.length()-2), r-2+k) == (Utils.power("0", r-2) + Utils.power("1", k)))
                {
                    return null;
                }
            }
            VertexClassification result = new VertexClassification();
            result.setInBit(1);
            result.addType(1);
            int [] degree = {1,0};
            result.setDegree(degree);
            return result;
        }
        return null;
    }
    
    
    private VertexClassification checkType1_3()
    {
        int i, j;
        i = 0;

        for (j = 0; j < label.length(); j++) {
            if (label.charAt(j) == '0') {
                i++;
            } else {
                break;
            }
        }
        if (!(i >= r-1 && i <= (n+r-4)/2)) {
            return null;
        }
        if(label.charAt(i+1) == '1' && 
           label.charAt(label.length() - 1) == '1')
        {
            for (int k = 0 ; k <= Math.min(n-r-i-1,(n-r-2)/2) ; k++)
            {
                if(Utils.suffex(label.substring(i+1, label.length()-2), r-2+k) == (Utils.power("0", r-2) + Utils.power("1", k)))
                {
                    return null;
                }
            }
            VertexClassification result = new VertexClassification();
            result.setInBit(1);
            result.addType(1);
            int [] degree = {1,0};
            result.setDegree(degree);
            return result;
        }
        return null;
    }
    
    
    private VertexClassification checkType1_4()
    {
        int i, j;
        i = 0;

        for (j = 0; j < label.length(); j++) {
            if (label.charAt(j) == '0') {
                i++;
            } else {
                break;
            }
        }
        if (!(i >= r-1 && i <= (n+r-4)/2)) {
            return null;
        }
        if(label.charAt(i+1) == '1' && 
           label.charAt(label.length() - 1) == '1' && 
           label.substring(i+2, label.length() -2) != Utils.power("1", (n-r-2)/2) + Utils.power("0",(n+r-2*i-4)/2))
        {
            for (int k = 0 ; k <= n-r-2*i-2 ; k++)
            {
                if(Utils.suffex(label.substring(i+1, label.length()-2), r-2+k) == (Utils.power("0", r-2) + Utils.power("1", (n-r)/2) + Utils.power("0", k)))
                {
                    return null;
                }
            }
            VertexClassification result = new VertexClassification();
            result.setInBit(1);
            result.addType(1);
            int [] degree = {1,0};
            result.setDegree(degree);
            return result;
        }
        return null;
    }
    
    
    private VertexClassification checkType2_1()
    {
        int i, j;
        
        i = 0;
        for (j = 0; j < r - 2 ; j++) {
            if (label.charAt(j) == '0') {
                i++;
            } else {
                break;
            }
        }
        if (i != r - 2) {
            return null;
        }
        
        i = 0;
        for (j = r - 2 ; j < r - 2 + ((n-r)/2) ; j++) {
            if (label.charAt(j) == '1') {
                i++;
            } else {
                break;
            }
        }
        if (i != (n - r )/2) {
            return null;
        }
        
        i = 0;
        for (j = r - 2 + ((n-r)/2) ; j < (n - r + 2)/2 ; j++) {
            if (label.charAt(j) == '0') {
                i++;
            } else {
                break;
            }
        }
        if (i != (n - r + 2)/2) {
            return null;
        }
        
        VertexClassification result = new VertexClassification();
        result.addType(2);
        int [] degree = {0,1};
        result.setDegree(degree);
        result.setOutBit(1);
        return result;
    }
    
    private VertexClassification checkType2_2()
    {
        int i, j;
        
        i = 0;
        for (j = 0; j < r - 2 ; j++) {
            if (label.charAt(j) == '0') {
                i++;
            } else {
                break;
            }
        }
        if (i != r - 2) {
            return null;
        }
        
        if (label.charAt(i) != '1') {
            return null;
        }
        
        i = 0;
        for (j = label.length() - 1 ; j > label.length() - 1 - ((n-r)/2) ; j--) {
            if (label.charAt(j) == '1') {
                i++;
            } else {
                break;
            }
        }
        if (i != (n - r)/2) {
            return null;
        }
        
        i = 0;
        for (j = j ; j < label.length() - 1 - ((n-r)/2) - (r - 2) ; j--) {
            if (label.charAt(j) == '0') {
                i++;
            } else {
                break;
            }
        }
        if (i != r - 2) {
            return null;
        }
        
        VertexClassification result = new VertexClassification();
        result.addType(2);
        result.addType(6);
        int [] degree = {0,1};
        result.setDegree(degree);
        result.setOutBit(1);
        return result;
    }
    
    
    private VertexClassification checkType2_3()
    {
        int i, j;
        
        i = 0;
        for (j = 0; j < r - 2 ; j++) {
            if (label.charAt(j) == '0') {
                i++;
            } else {
                break;
            }
        }
        if (i != r - 2) {
            return null;
        }
        
        if (label.charAt(i) != '1') {
            return null;
        }
        
        i = 0;
        for (j = label.length() - 1 ; j > label.length() - 1 - ((n - 3*r + 4)/2) ; j--) {
            if (label.charAt(j) == '0') {
                i++;
            } else {
                break;
            }
        }
        if (!(i >= 1  && i <= (n - 3*r + 4)/2)) {
            return null;
        }
        
        int ii = i;
        i = 0;
        for (j = j ; j > label.length() - 1 - ((n-r)/2) - ii ; j--) {
            if (label.charAt(j) == '1') {
                i++;
            } else {
                break;
            }
        }
        if (i != (n - r)/2) {
            return null;
        }
        
        i = 0;
        for (j = j ; j < label.length() - 1 - ((n-r)/2) - (r - 2) - ii ; j--) {
            if (label.charAt(j) == '0') {
                i++;
            } else {
                break;
            }
        }
        if (i != r - 2) {
            return null;
        }
        
        VertexClassification result = new VertexClassification();
        result.addType(2);
        result.addType(7);
        int [] degree = {0,1};
        result.setDegree(degree);
        result.setOutBit(1);
        return result;
    }
    
    private VertexClassification checkType2_4()
    {
        int i, j;
        
        i = 0;
        for (j = 0; j < r - 2 ; j++) {
            if (label.charAt(j) == '0') {
                i++;
            } else {
                break;
            }
        }
        if (i != r - 2) {
            return null;
        }
        
        if (label.charAt(i) != '1') {
            return null;
        }
        
        if (label.charAt(label.length() -1) != '0') {
            return null;
        }
        
        VertexClassification result = new VertexClassification();
        result.addType(2);
        int [] degree = {1,1};
        result.setDegree(degree);
        result.setInBit(1);
        result.setOutBit(1);
        return result;
    }
    
    private VertexClassification checkType2_5()
    {
        int i, j;
        
        i = 0;
        for (j = 0; j < r - 2 ; j++) {
            if (label.charAt(j) == '0') {
                i++;
            } else {
                break;
            }
        }
        if (i != r - 2) {
            return null;
        }
        
        if (label.charAt(i) != '1') {
            return null;
        }
        
        i = 0;
        for (j = label.length() - 1 ; j > label.length() - 1 - ((n-r)/2) ; j--) {
            if (label.charAt(j) == '1') {
                i++;
            } else {
                break;
            }
        }
        if (i != (n - r)/2) {
            return null;
        }
        
        VertexClassification result = new VertexClassification();
        result.addType(2);
        int [] degree = {1,1};
        result.setDegree(degree);
        result.setInBit(1);
        result.setOutBit(1);
        return result;
    }
    
    private VertexClassification checkType2_6()
    {
        int i, j;
        
        i = 0;
        for (j = 0; j < r - 2 ; j++) {
            if (label.charAt(j) == '0') {
                i++;
            } else {
                break;
            }
        }
        if (i != r - 2) {
            return null;
        }
        
        if (label.charAt(i) != '1') {
            return null;
        }
        
        i = 0;
        for (j = label.length() - 1 ; j > label.length() - 1 - ((n-r)/2) ; j--) {
            if (label.charAt(j) == '1') {
                i++;
            } else {
                break;
            }
        }
        if (!(i >= 1 && i <= (n - r - 2)/2)) {
            return null;
        }
        
        int ii = i;
        i = 0;
        
        for (j = j ; j > label.length() - 1 - (r-3) - ii ; j--) {
            if (label.charAt(j) == '0') {
                i++;
            } else {
                break;
            }
        }
        if (!(i >= 1 && i <= r - 3)) {
            return null;
        }
        
        if (label.charAt(j) != '1') {
            return null;
        }
        
        
        VertexClassification result = new VertexClassification();
        result.addType(2);
        int [] degree = {1,1};
        result.setDegree(degree);
        result.setInBit(1);
        result.setOutBit(1);
        return result;
    }

    /**
     * 0^i 1 x 0^(r - 2) 1^((n - r)/2) o^j
     * for some i in [1...r - 3]
     * for some j in [r - i - 2...((n - r - 2i)/2)]
     */
    private VertexClassification checkType37(){
        int counter = 0;  //counter used to keep track of number of characters
        int currIndex = 0; //start at beginning of string for prefix

        //bounds for i and j
        int iLower = 1;
        int iUpper = r - 3;
        int jLower; //we need the value of i to calculate these
        int jUpper;

        /**
         * Check prefix------------------------------------------------------------
         */

        VertexClassification result = new VertexClassification();
        result.addType(37);
        int [] degree = {0,1};
        result.setDegree(degree);

        return result;
    }

    /**
     * 0^(n - 2) 1
     */
    private VertexClassification checkType4_1(){
        //Check that the label ends with a 1
        if(label.charAt(label.length() - 1) != '1')
            return null;

        //Check that there are (n - 2) 0's at start of label
        int bound = r - 2;
        for(int i = 0; i < bound; i++){
           if(label.charAt(i) != '0')
               return null;
        }

        VertexClassification result = new VertexClassification();
        result.addType(4);
        int [] degree = {1,0};
        result.setDegree(degree);

        return result;
    }

    /**
     * 0^i 1 x 0^(r - 2) 1 for some i in [0...r - 3]
     */
    private VertexClassification checkType4_2(){
        int counter = 0;  //counter used to keep track of number of characters
        int currIndex = 0; //start at beginning of string for prefix

        //bounds for i and j
        int iLower = 0;
        int iUpper = r - 3;

        /**
         * Check prefix------------------------------------------------------------
         */

        //Count the nubmer of 0's at start of prefix
        for(int i = currIndex; i < label.length(); i++){
            if(label.charAt(i) == '0'){
                counter ++;
                currIndex ++;
            }
            else
                break;
        }

        //Check that there are i number of 0's
        boolean legalRange = false;
        for(int i = iLower; i <= iUpper && !legalRange; i++){
            if(counter == i)
                legalRange = true;
        }

        //Check to see if there is a 1 after 0's
        if(label.charAt(currIndex) != '1')
            return null;

        /**
         * Check suffix-------------------------------------------------------------
         */

        //change current index to the end of the label and set counter to 0
        currIndex = label.length() - 1;

        //check to see if there is a 1 at end of label
        if(label.charAt(currIndex) != '1')
            return null;

        currIndex --;  //move down one to compensate for checking the 1 at end of label

        //Check that there are (r - 2) 0's before 1
        int bound = currIndex - (r - 2);
        for(int i = currIndex; i > bound; i--){
            if(label.charAt(i) == '0'){
                currIndex --;
            }
            else
                return null;
        }

        VertexClassification result = new VertexClassification();
        result.addType(4);
        int [] degree = {1,1};
        result.setDegree(degree);

        return result;
    }

    /**
     * 0^i 1 x 0^(r - 2) 1^j for some i in [0...r - 3] and j in [2...((n - r -2)/2)].
     */
    private VertexClassification checkType5(){
        int counter = 0;  //counter used to keep track of number of characters
        int currIndex = 0; //start at beginning of string for prefix

        //bounds for i and j
        int iLower = 0;
        int iUpper = r - 3;
        int jLower = 2;
        int jUpper = (n - r - 2)/2;

        /**
         * Check prefix------------------------------------------------------------
         */

        //Count the nubmer of 0's at start of prefix
        for(int i = currIndex; i < label.length(); i++){
            if(label.charAt(i) == '0'){
                counter ++;
                currIndex ++;
            }
            else
                break;
        }

        //Check that there are i number of 0's
        boolean legalRange = false;
        for(int i = iLower; i <= iUpper && !legalRange; i++){
            if(counter == i)
                legalRange = true;
        }

        //Check to see if there is a 1 after 0's
        if(label.charAt(currIndex) != '1')
            return null;

        /**
         * Check suffix-------------------------------------------------------------
         */

        //change current index to the end of the label and set counter to 0
        currIndex = label.length() - 1;
        counter = 0;

        //count the number of 1's at the end of the label
        for(int i = currIndex; i >= 0; i--){
            if(label.charAt(i) == '1'){
                counter ++;
                currIndex --;
            }else
                break;
        }

        //Check to see if the number of 1's is within range of j in equation
        legalRange = false;
        for(int i = jLower; i <= jUpper && !legalRange; i++){
            if(counter == i)
                legalRange = true;
        }

        //If not legel number of zeroes, return null
        if(!legalRange)
            return null;

        //Check that there are (r - 2) 0's before 1's
        int bound = currIndex - (r - 2);
        for(int i = currIndex; i > bound; i--){
            if(label.charAt(i) == '0'){
                currIndex --;
            }
            else
                return null;
        }

        VertexClassification result = new VertexClassification();
        result.addType(5);
        int [] degree = {0,1};
        result.setDegree(degree);

        return result;
    }

    /**
     * 0^i 1 x 0^(r - 2) 1^[(n - r)/2] for some i in [0...r-3].
     */
    private VertexClassification checkType6(){
        int counter = 0;  //counter used to keep track of number of characters
        int currIndex = 0; //start at beginning of string for prefix

        //bounds for i
        int iLower = 0;
        int iUpper = r - 3;

        /**
         * Check prefix------------------------------------------------------------
         */

        //Count the number of 0's in front of prefix
        for(int i = currIndex; i < label.length(); i ++){
            if(label.charAt(i) == '0'){
                counter ++;
                currIndex = i + 1; //move up an index
            }
            else
                break;
        }

        //Check that there are i number of 0's
        boolean legalRange = false;
        for(int i = iLower; i <= iUpper && !legalRange; i++){
            if(counter == i)
                legalRange = true;
        }

        //Check to see if there is a 1 after 0's
        if(label.charAt(currIndex) != '1')
            return null;

        /**
         * Check suffix-------------------------------------------------------------
         */

        //change current index to the end of the label
        currIndex = label.length() - 1;

        //check that there are (n - r)/2 1's at end of label
        int bound = currIndex - ((n - r)/2);
        for(int i = currIndex; i > bound; i--){
            if(label.charAt(i) == '1'){
                currIndex --;
            }
            else
                return null;
        }

        //check that there are (r - 2) 0's before 1's
        bound = currIndex - (r - 2);
        for(int i = currIndex; i > bound; i--){
            if(label.charAt(i) == '0'){
                currIndex --;
            }
            else
                return null;
        }

        VertexClassification result = new VertexClassification();
        result.addType(6);
        int [] degree = {0,1};
        result.setDegree(degree);

        return result;
    }

    /**
     * 1 x 0^(r - 2) 1^[(n - r)/2] 0^i for some i in [1...(r - 3)].
     */
    private VertexClassification checkType7_1(){
        int counter = 0;  //Used to count number of characters
        int currIndex = label.length() - 1;  //start at end of label

        //bounds for i
        int iLower = 1;
        int iUpper = r -3;
        /**
         * Check prefix------------------------------------------------------------
         */

        //Make sure label starts with '1'
        if((label.charAt(0) != '1'))
            return null;

        /**
         * Check suffix-------------------------------------------------------------
         */

        //Count the number of 0's at the end of the label
        for(int i = currIndex; i >= 0; i--){
            if(label.charAt(i) == '0'){
                counter ++;
                currIndex --;
            }else
                break;
        }

        //Check to see if the number of zeros is within range of i in equation
        boolean legalRange = false;
        for(int i = iLower; i <= iUpper && !legalRange; i++){
            if(counter == i)
                legalRange = true;
        }

        //If not legel number of zeroes, return null
        if(!legalRange)
            return null;

        //check that there are (n - r)/2 1's at end of label
        int bound = currIndex - ((n - r)/2);
        for(int i = currIndex; i > bound; i--){
            if(label.charAt(i) == '1'){
                currIndex --;
            }
            else
                return null;
        }

        //check that there are (r - 2) 0's before 1's
        bound = currIndex - (r - 2);
        for(int i = currIndex; i > bound; i--){
            if(label.charAt(i) == '0'){
                currIndex --;
            }
            else
                return null;
        }

        VertexClassification result = new VertexClassification();
        result.addType(7);
        int [] degree = {0,1};
        result.setDegree(degree);

        return result;
    }

    /**
     * 0 1 x 0^(r - 2) 1^[(n - r)/2] 0^i for some i in [1...(r - 4)].
     */
    private VertexClassification checkType7_2(){
        int counter = 0;  //Used to count number of characters
        int currIndex = label.length() - 1;  //start at end of label

        //Bounds for i
        int iLower = 1;
        int iUpper = 4;

        /**
         * Check prefix------------------------------------------------------------
         */

        //Make sure label starts with '01'
        if((label.charAt(0) != '0') || (label.charAt(1) != '1'))
            return null;

        /**
         * Check suffix-------------------------------------------------------------
         */

        //Count the number of 0's at the end of the label
        for(int i = currIndex; i >= 0; i--){
            if(label.charAt(i) == '0'){
                counter ++;
                currIndex --;
            }else
                break;
        }

        //Check to see if the number of zeros is within range of i in equation
        boolean legalRange = false;
        for(int i = iLower; i <= iUpper && !legalRange; i++){
            if(counter == i)
                legalRange = true;
        }

        //If not legel number of zeroes, return null
        if(!legalRange)
            return null;

        //check that there are (n - r)/2 1's at end of label
        int bound = currIndex - ((n - r)/2);
        for(int i = currIndex; i > bound; i--){
            if(label.charAt(i) == '1'){
                currIndex --;
            }
            else
                return null;
        }

        //check that there are (r - 2) 0's before 1's
        bound = currIndex - (r - 2);
        for(int i = currIndex; i > bound; i--){
            if(label.charAt(i) == '0'){
                currIndex --;
            }
            else
                return null;
        }

        VertexClassification result = new VertexClassification();
        result.addType(7);
        int [] degree = {0,1};
        result.setDegree(degree);

        return result;
    }
}
