package csc656;

import java.util.Arrays;

public class Table3 {

    private final int n;
    private final int r;
    private String currLabel;
    private Vertex currVertex;

    public Table3(int n, int r) {
        this.n = n;
        this.r = r;
    }

    public void checkType(Vertex vertex) throws SubTypeNotFound,
            DegreeMismatch, InOutBitMismatch {
        this.currVertex = vertex;
        this.currLabel = this.currVertex.getLabel();
        int[] vertexType = this.currVertex.getVertexClassification().getType();
        switch (vertexType[0]) {
            case 1:
                checkType1();
                break;
            case 2:
                checkType2();
                break;
            case 3:
                checkType3();
                break;
            case 4:
                checkType4();
                break;
            case 5:
                checkType5();
                break;
            case 6:
                checkType6();
                break;
            case 7:
                checkType7();
                break;
            default:
                if (!(vertex.getInEdgesCount() == 2
                        || vertex.getOutEdgesCount() == 2)) {
                    throw new SubTypeNotFound("Vertex " + this.currLabel
                            + " does not have a matching subtype");
                }
        }
        if(currVertex.getVertexClassification().getDegree()[0]!=
                currVertex.getInEdgesCount() ||
                currVertex.getVertexClassification().getDegree()[1]!=
                currVertex.getOutEdgesCount()){
            throw new DegreeMismatch("Vertex " + currLabel + 
                    " has Graph degree: [" + currVertex.getInEdgesCount()+ 
                    ", " + currVertex.getOutEdgesCount() + 
                    "] but table 3 degree: " + 
                    Arrays.toString(currVertex.getVertexClassification().getDegree())
            );
        }

        //Check in-bit
        if(currVertex.getInEdges().size() == 1) {
            int t3InBit = currVertex.getVertexClassification().getInBit();
            String inEdgeStr = currVertex.getInEdges().get(0).getLabel();
            int vInBit = Character.getNumericValue(inEdgeStr.charAt(0));

            if (t3InBit != vInBit) {
                throw new InOutBitMismatch("Vertex " + currLabel + " has an in bit of " +
                        vInBit + " but table 3 in bit of " + t3InBit);
            }
        }

        //Check out-bit
        if(currVertex.getOutEdges().size() == 1) {
            int t3OutBit = currVertex.getVertexClassification().getOutBit();
            String outEdgeStr = currVertex.getOutEdges().get(0).getLabel();
            int vOutBit = Character.getNumericValue(outEdgeStr.charAt(outEdgeStr.length() - 1));

            if(t3OutBit != vOutBit){
                throw new InOutBitMismatch("Vertex " + currLabel + " has an out bit of " +
                        vOutBit + " but table 3 out bit of " + t3OutBit);
            }
        }
    }

    private void checkType1() throws SubTypeNotFound {
        if (checkType1_1()) {
            this.currVertex.getVertexClassification().setInBit(1);
            int[] degree = {1, 0};
            this.currVertex.getVertexClassification().setDegree(degree);
        } else if (checkType1_2()) {
            this.currVertex.getVertexClassification().setInBit(1);
            int[] degree = {1, 0};
            this.currVertex.getVertexClassification().setDegree(degree);
        } else if (checkType1_3()) {
            this.currVertex.getVertexClassification().setInBit(1);
            int[] degree = {1, 0};
            this.currVertex.getVertexClassification().setDegree(degree);
        } else if (checkType1_4()) {
            this.currVertex.getVertexClassification().setInBit(1);
            int[] degree = {1, 0};
            this.currVertex.getVertexClassification().setDegree(degree);
        } else {
            throw new SubTypeNotFound("Type 1 vertex " + this.currLabel
                    + " does not match a type 1 subtype");
        }
    }

    private void checkType2() throws SubTypeNotFound {
        if (currVertex.getVertexClassification().getType().length == 2
                && currVertex.getVertexClassification().getType()[1] == 6
                && checkType26()) {
            int[] degree = {0, 1};
            this.currVertex.getVertexClassification().setDegree(degree);
            this.currVertex.getVertexClassification().setOutBit(1);
        } else if (currVertex.getVertexClassification().getType().length == 2
                && currVertex.getVertexClassification().getType()[1] == 7
                && checkType27()) {
            int[] degree = {0, 1};
            this.currVertex.getVertexClassification().setDegree(degree);
            this.currVertex.getVertexClassification().setOutBit(1);
        } else if (checkType2_1()) {
            int[] degree = {0, 1};
            currVertex.getVertexClassification().setDegree(degree);
            currVertex.getVertexClassification().setOutBit(1);
        } else if (checkType2_4()) {
            int[] degree = {1, 1};
            currVertex.getVertexClassification().setDegree(degree);
            currVertex.getVertexClassification().setInBit(1);
            currVertex.getVertexClassification().setOutBit(1);
        } else if (checkType2_5()) {
            int[] degree = {1, 1};
            currVertex.getVertexClassification().setDegree(degree);
            currVertex.getVertexClassification().setInBit(1);
            currVertex.getVertexClassification().setOutBit(1);
        } else if (checkType2_6()) {
            int[] degree = {1, 1};
            currVertex.getVertexClassification().setDegree(degree);
            currVertex.getVertexClassification().setInBit(1);
            currVertex.getVertexClassification().setOutBit(1);
        } else {
            throw new SubTypeNotFound("Type 2(6)(7) vertex " + this.currLabel
                    + " does not match a type 2 subtype");
        }
    }

    private void checkType3() throws SubTypeNotFound {
        if (currVertex.getVertexClassification().getType().length == 2
                && currVertex.getVertexClassification().getType()[1] == 7
                && checkType37()) {
            int[] degree = {0, 1};
            this.currVertex.getVertexClassification().setDegree(degree);
            this.currVertex.getVertexClassification().setOutBit(1);
        } else if (checkType3_1()) {
            int[] degree = {1, 1};
            this.currVertex.getVertexClassification().setDegree(degree);
            this.currVertex.getVertexClassification().setInBit(1);
            this.currVertex.getVertexClassification().setOutBit(0);
        } else if (checkType3_2()) {
            int[] degree = {1, 1};
            this.currVertex.getVertexClassification().setDegree(degree);
            this.currVertex.getVertexClassification().setInBit(1);
            this.currVertex.getVertexClassification().setOutBit(1);
        }else {
            throw new SubTypeNotFound("Type 3 vertex " + this.currLabel
                    + " does not match a type 3 subtype");
        }
    }

    private void checkType4() throws SubTypeNotFound {
        if (checkType4_1()) {
            int[] degree = {1, 0};
            this.currVertex.getVertexClassification().setDegree(degree);
            this.currVertex.getVertexClassification().setInBit(0);
        }
        else if (checkType4_2()) {
            int[] degree = {1, 1};
            this.currVertex.getVertexClassification().setDegree(degree);
            this.currVertex.getVertexClassification().setInBit(0);
            this.currVertex.getVertexClassification().setOutBit(0);
        }
        else {
            throw new SubTypeNotFound("Type 4 vertex " + this.currLabel
                    + " does not match a type 4 subtype");
        }
    }

    private void checkType5() throws SubTypeNotFound {
        if (checkType5_1()) {
            int[] degree = {0, 1};
            this.currVertex.getVertexClassification().setDegree(degree);
            this.currVertex.getVertexClassification().setOutBit(0);
        } else {
            throw new SubTypeNotFound("Type 5 vertex " + this.currLabel
                    + " does not match a type 5 subtype");
        }
    }

    private void checkType6() throws SubTypeNotFound {
        if (checkType6_1()) {
            int[] degree = {0, 1};
            this.currVertex.getVertexClassification().setDegree(degree);
            this.currVertex.getVertexClassification().setOutBit(1);
        } else {
            throw new SubTypeNotFound("Type 6 vertex " + this.currLabel
                    + " does not match a type 6 subtype");
        }
    }

    private void checkType7() throws SubTypeNotFound {
        if (checkType7_1()) {
            int[] degree = {0, 1};
            this.currVertex.getVertexClassification().setDegree(degree);
            this.currVertex.getVertexClassification().setOutBit(1);
        } else {
            throw new SubTypeNotFound("Type 7 vertex " + this.currLabel
                    + " does not match a type 7 subtype");
        }
    }

    private boolean checkType1_1() {
        int i, j;
        i = 0;

        for (j = 0; j < currLabel.length(); j++) {
            if (currLabel.charAt(j) == '0') {
                i++;
            } else {
                break;
            }
        }
        if (!(i >= (n + r - 2) / 2 && i <= n - 3)) {
            return false;
        }
        if (currLabel.charAt(i) == '1'
                && currLabel.charAt(currLabel.length() - 1) == '0') {
            return true;
        }
        return false;
    }

    private boolean checkType1_2() {
        int i, j;
        i = 0;

        for (j = 0; j < currLabel.length(); j++) {
            if (currLabel.charAt(j) == '0') {
                i++;
            } else {
                break;
            }
        }
        if (!(i >= (n + r - 2) / 2 && i <= n - 3)) {
            return false;
        }
        if (currLabel.charAt(i) == '1'
                && currLabel.charAt(currLabel.length() - 1) == '1'
                && currLabel.substring(i + 1, currLabel.length() - 2)
                != Utils.power("1", n - i - 3)) {
            for (int k = 0; k <= n - r - i - 1; k++) {
                if (Utils.suffex(currLabel.
                        substring(i + 1, currLabel.length() - 2), r - 2 + k)
                        == (Utils.power("0", r - 2) + Utils.power("1", k))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean checkType1_3() {
        int i, j;
        i = 0;

        for (j = 0; j < currLabel.length(); j++) {
            if (currLabel.charAt(j) == '0') {
                i++;
            } else {
                break;
            }
        }
        if (!(i >= r - 1 && i <= (n + r - 4) / 2)) {
            return false;
        }
        if (currLabel.charAt(i) == '1'
                && currLabel.charAt(currLabel.length() - 1) == '1') {
            for (int k = 0; k <= Math.min(n - r - i - 1, (n - r - 2) / 2); k++) {
                if (Utils.suffex(currLabel.
                        substring(i + 1, currLabel.length() - 2), r - 2 + k)
                        == (Utils.power("0", r - 2) + Utils.power("1", k))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean checkType1_4() {
        int i, j;
        i = 0;

        for (j = 0; j < currLabel.length(); j++) {
            if (currLabel.charAt(j) == '0') {
                i++;
            } else {
                break;
            }
        }
        if (!(i >= r - 1 && i <= (n + r - 4) / 2)) {
            return false;
        }
        if (currLabel.charAt(i) == '1'
                && currLabel.charAt(currLabel.length() - 1) == '0'
                && currLabel.substring(i + 1, currLabel.length() - 2)
                != Utils.power("1", (n - r - 2) / 2) + Utils.power("0", (n + r - 2 * i - 4) / 2)) {
            for (int k = 0; k <= n - r - 2 * i - 2; k++) {
                if (Utils.suffex(currLabel.
                        substring(i + 1, currLabel.length() - 2), r - 2 + k)
                        == (Utils.power("0", r - 2)
                        + Utils.power("1", (n - r) / 2)
                        + Utils.power("0", k))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean checkType2_1() {
        int i, j;

        i = 0;
        for (j = 0; j < currLabel.length(); j++) {
            if (currLabel.charAt(j) == '0') {
                i++;
            } else {
                break;
            }
        }
        if (i != r - 2) {
            return false;
        }

        i = 0;
        for (; j < currLabel.length(); j++) {
            if (currLabel.charAt(j) == '1') {
                i++;
            } else {
                break;
            }
        }
        if (i != (n - r) / 2) {
            return false;
        }

        i = 0;
        for (; j < currLabel.length(); j++) {
            if (currLabel.charAt(j) == '0') {
                i++;
            } else {
                break;
            }
        }
        if (i != (n - r + 2) / 2) {
            return false;
        }
        return true;
    }

    private boolean checkType26() {
        int i, j;

        // check number of 0's at the start of the label
        i = 0;
        for (j = 0; j < r - 2; j++) {
            if (currLabel.charAt(j) == '0') {
                i++;
            } else {
                break;
            }
        }
        if (i != r - 2) {
            return false;
        }

        // make sure 0's are followed by a '1'
        if (currLabel.charAt(j) != '1') {
            return false;
        }

        // check number of 1's at the end of the label
        i = 0;
        for (j = currLabel.length() - 1; j > currLabel.length() - 1 - ((n - r) / 2); j--) {
            if (currLabel.charAt(j) == '1') {
                i++;
            } else {
                break;
            }
        }
        if (i != ((n - r) / 2)) {
            return false;
        }

        // check the number of 0's preceding the one's above
        i = 0;
        for (; j > currLabel.length() - 1 - ((n - r) / 2) - (r - 2); j--) {
            if (currLabel.charAt(j) == '0') {
                i++;
            } else {
                break;
            }
        }
        if (i != r - 2) {
            return false;
        }
        return true;
    }

    private boolean checkType27() {
        int i, j;
        
        // check number of zero's at the end of the label and assign i
        i=0;
        for(j=currLabel.length()-1; j>=0;j--){
            if(currLabel.charAt(j)=='0'){
                i++;
            }else{
                break;
            }
        }
        if(!(i>=1 && i<=((n-(3*r)+4)/2))){
            return false;
        }
        
        // check ones preceding 0's above
        int oneCount=0;
        for(;j>=currLabel.length()-i-((n-r)/2); j--){
            if(currLabel.charAt(j)=='1'){
                oneCount++;
            }else{
                break;
            }
        }
        if(oneCount!=((n-r)/2)){
            return false;
        }
        
        // check 0's preceding ones above
        int preXZeroCount=0;
        for(;j>=currLabel.length()-i-((n-r)/2)-(r-2); j--){
            if(currLabel.charAt(j)=='0'){
                preXZeroCount++;
            }else{
                break;
            }
        }
        if(preXZeroCount!=(r-2)){
            return false;
        }
        
        // check number of starting 0's and that they are followed by a one
        i = 0;
        for (j = 0; j < r - 2; j++) {
            if (currLabel.charAt(j) == '0') {
                i++;
            } else {
                break;
            }
        }
        if (i != r-2 || currLabel.charAt(j)!='1') {
            return false;
        }
        
        return true;
    }

    private boolean checkType2_4() {
        int i, j;

        i = 0;
        for (j = 0; j < r - 2; j++) {
            if (currLabel.charAt(j) == '0') {
                i++;
            } else {
                break;
            }
        }
        if (i != r - 2) {
            return false;
        }

        if (currLabel.charAt(i) != '1') {
            return false;
        }

        if (currLabel.charAt(currLabel.length() - 1) != '0') {
            return false;
        }
        return true;
    }

    private boolean checkType2_5() {
        int i, j;

        i = 0;
        for (j = 0; j < r - 2; j++) {
            if (currLabel.charAt(j) == '0') {
                i++;
            } else {
                break;
            }
        }
        if (i != r - 2) {
            return false;
        }

        if (currLabel.charAt(i) != '1') {
            return false;
        }

        i = 0;
        for (j = currLabel.length() - 1; j > currLabel.length() - 1 - ((n - r) / 2); j--) {
            if (currLabel.charAt(j) == '1') {
                i++;
            } else {
                break;
            }
        }
        if (i != (n - r) / 2) {
            return false;
        }
        return true;
    }

    private boolean checkType2_6() {
        int i, j;

        i = 0;
        for (j = 0; j < r - 2; j++) {
            if (currLabel.charAt(j) == '0') {
                i++;
            } else {
                break;
            }
        }
        if (i != r - 2) {
            return false;
        }

        if (currLabel.charAt(i) != '1') {
            return false;
        }

        i = 0;
        for (j = currLabel.length() - 1; j > currLabel.length() - 1 - ((n - r) / 2); j--) {
            if (currLabel.charAt(j) == '1') {
                i++;
            } else {
                break;
            }
        }
        if (!(i >= 1 && i <= (n - r - 2) / 2)) {
            return false;
        }

        int ii = i;
        i = 0;

        for (j = j; j > currLabel.length() - 1 - (r - 3) - ii; j--) {
            if (currLabel.charAt(j) == '0') {
                i++;
            } else {
                break;
            }
        }
        if (!(i >= 1 && i <= r - 3)) {
            return false;
        }

        if (currLabel.charAt(j) != '1') {
            return false;
        }
        return true;
    }

    /**
     * 1 x 0(r - 2) not compatible with 'hole'^((n - r - 2j)/2) 0^(r - 2) 1^((n
     * - r)/2) 0^(j + 1) for all j in [0...((n - r - 2)/2)]
     */
    private boolean checkType3_1() {
        int currIndex = currLabel.length() - 1; //start at end of string for suffix
        Utils utils = new Utils();  //used to check compatibility

        //bounds for j
        int jLower = 0;
        int jUpper = (n - r - 2) / 2;

        //Confirm string 1 x 0^(r - 2)
        //check that it starts with a 1
        if (currLabel.charAt(0) != '1') {
            return false;
        }

        //check that it ends with (r - 2) 0's
        int bound = currIndex - (r - 2);
        for (int i = currIndex; i > bound; i--) {
            if (currLabel.charAt(i) == '0') {
                currIndex--;
            } else {
                return false;
            }
        }

        //Check that it is not compatible with equation in method comment for all j
        for (int j = jLower; j <= jUpper; j++) {
            String jStr = buildJString(j);
            if (utils.isCompatible(currLabel, jStr)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 1 x 0(r - i - 2) not compatible with 'hole'^((n - r - 2j)/2) 0^(r - 2) 1^((n
     * - r)/2) 0^(j + 1) for all j in [0...((n - r - 2)/2)]
     */
    private boolean checkType3_2() {
        int currIndex = 0; //start at end of string for suffix
        Utils utils = new Utils();  //used to check compatibility
        int counter = 0;
        int iVal = 0;
        //bounds for i
        int iLower = 1;
        int iUpper = r - 3;

        //bounds for j
        int jLower = 0;
        int jUpper = (n - r - 2) / 2;

        /**
         * Check
         * prefix------------------------------------------------------------
         */
        //Count the number of 0's in front of prefix
        for (int i = currIndex; i < currLabel.length(); i++) {
            if (currLabel.charAt(i) == '0') {
                counter++;
                currIndex = i + 1; //move up an index
            } else {
                break;
            }
        }

        //Check that there are i number of 0's
        boolean legalRange = false;
        for (int i = iLower; i <= iUpper && !legalRange; i++) {
            if (counter == i) {
                iVal = i; //Set value of i used to get the bounds of j
                legalRange = true;
            }
        }

        //Check to see if there is a 1 after 0's
        if (currLabel.charAt(currIndex) != '1') {
            return false;
        }

        /**
         * Check
         * suffix------------------------------------------------------------
         */
        //change current index to the end of the label
        currIndex = currLabel.length() - 1;

        //check that it ends with (r - i - 2) 0's
        int bound = currIndex - (r - iVal - 2);
        for (int i = currIndex; i > bound; i--) {
            if (currLabel.charAt(i) == '0') {
                currIndex--;
            } else {
                return false;
            }
        }

        //Check that it is not compatible with equation in method comment for all j
        for (int j = jLower; j <= jUpper; j++) {
            String jStr = buildJString(j);
            if (utils.isCompatible(currLabel, jStr)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 0^i 1 x 0^(r - 2) 1^((n - r)/2) o^j for some i in [1...r - 3] for some j
     * in [r - i - 2...((n - r - 2i)/2)]
     */
    private boolean checkType37() {
        int counter = 0;  //counter used to keep track of number of characters
        int currIndex = 0; //start at beginning of string for prefix
        int iVal = 0; //Value for i used to calculate bounds for j

        //bounds for i and j
        int iLower = 1;
        int iUpper = r - 3;
        int jLower; //we need the value of i to calculate these
        int jUpper;

        /**
         * Check
         * prefix------------------------------------------------------------
         */
        //Count the number of 0's in front of prefix
        for (int i = currIndex; i < currLabel.length(); i++) {
            if (currLabel.charAt(i) == '0') {
                counter++;
                currIndex = i + 1; //move up an index
            } else {
                break;
            }
        }

        //Check that there are i number of 0's
        boolean legalRange = false;
        for (int i = iLower; i <= iUpper && !legalRange; i++) {
            if (counter == i) {
                iVal = i; //Set value of i used to get the bounds of j
                legalRange = true;
            }
        }

        //Check to see if there is a 1 after 0's
        if (currLabel.charAt(currIndex) != '1') {
            return false;
        }

        /**
         * Check
         * suffix-------------------------------------------------------------
         */
        jLower = r - iVal - 2;
        jUpper = (n - r - 2 * iVal) / 2;

        //change current index to the end of the label
        currIndex = currLabel.length() - 1;

        //Count the number of 0's at the end of the label
        for (int i = currIndex; i >= 0; i--) {
            if (currLabel.charAt(i) == '0') {
                counter++;
                currIndex--;
            } else {
                break;
            }
        }

        //Check to see if the number of zeros is within range of i in equation
        legalRange = false;
        for (int i = jLower; i <= jUpper && !legalRange; i++) {
            if (counter == i) {
                legalRange = true;
            }
        }

        //check that there are (n - r)/2 1's at end of label
        int bound = currIndex - ((n - r) / 2);
        for (int i = currIndex; i > bound; i--) {
            if (currLabel.charAt(i) == '1') {
                currIndex--;
            } else {
                return false;
            }
        }

        //check that there are (r - 2) 0's before 1's
        bound = currIndex - (r - 2);
        for (int i = currIndex; i > bound; i--) {
            if (currLabel.charAt(i) == '0') {
                currIndex--;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 0^(n - 2) 1
     */
    private boolean checkType4_1() {
        //Check that the label ends with a 1
        if (currLabel.charAt(currLabel.length() - 1) != '1') {
            return false;
        }

        //Check that there are (n - 2) 0's at start of label
        int bound = r - 2;
        for (int i = 0; i < bound; i++) {
            if (currLabel.charAt(i) != '0') {
                return false;
            }
        }
        return true;
    }

    /**
     * 0^i 1 x 0^(r - 2) 1 for some i in [0...r - 3]
     */
    private boolean checkType4_2() {
        int counter = 0;  //counter used to keep track of number of characters
        int currIndex = 0; //start at beginning of string for prefix

        //bounds for i and j
        int iLower = 0;
        int iUpper = r - 3;

        /**
         * Check
         * prefix------------------------------------------------------------
         */
        //Count the nubmer of 0's at start of prefix
        for (int i = currIndex; i < currLabel.length(); i++) {
            if (currLabel.charAt(i) == '0') {
                counter++;
                currIndex++;
            } else {
                break;
            }
        }

        //Check that there are i number of 0's
        boolean legalRange = false;
        for (int i = iLower; i <= iUpper && !legalRange; i++) {
            if (counter == i) {
                legalRange = true;
            }
        }

        //Check to see if there is a 1 after 0's
        if (currLabel.charAt(currIndex) != '1') {
            return false;
        }

        /**
         * Check
         * suffix-------------------------------------------------------------
         */
        //change current index to the end of the label and set counter to 0
        currIndex = currLabel.length() - 1;

        //check to see if there is a 1 at end of label
        if (currLabel.charAt(currIndex) != '1') {
            return false;
        }

        currIndex--;  //move down one to compensate for checking the 1 at end of label

        //Check that there are (r - 2) 0's before 1
        int bound = currIndex - (r - 2);
        for (int i = currIndex; i > bound; i--) {
            if (currLabel.charAt(i) == '0') {
                currIndex--;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 0^i 1 x 0^(r - 2) 1^j for some i in [0...r - 3] and j in [2...((n - r
     * -2)/2)].
     */
    private boolean checkType5_1() {
        int counter = 0;  //counter used to keep track of number of characters
        int currIndex = 0; //start at beginning of string for prefix

        //bounds for i and j
        int iLower = 0;
        int iUpper = r - 3;
        int jLower = 2;
        int jUpper = (n - r - 2) / 2;

        /**
         * Check
         * prefix------------------------------------------------------------
         */
        //Count the nubmer of 0's at start of prefix
        for (int i = currIndex; i < currLabel.length(); i++) {
            if (currLabel.charAt(i) == '0') {
                counter++;
                currIndex++;
            } else {
                break;
            }
        }

        //Check that there are i number of 0's
        boolean legalRange = false;
        for (int i = iLower; i <= iUpper && !legalRange; i++) {
            if (counter == i) {
                legalRange = true;
            }
        }

        //Check to see if there is a 1 after 0's
        if (currLabel.charAt(currIndex) != '1') {
            return false;
        }

        /**
         * Check
         * suffix-------------------------------------------------------------
         */
        //change current index to the end of the label and set counter to 0
        currIndex = currLabel.length() - 1;
        counter = 0;

        //count the number of 1's at the end of the label
        for (int i = currIndex; i >= 0; i--) {
            if (currLabel.charAt(i) == '1') {
                counter++;
                currIndex--;
            } else {
                break;
            }
        }

        //Check to see if the number of 1's is within range of j in equation
        legalRange = false;
        for (int i = jLower; i <= jUpper && !legalRange; i++) {
            if (counter == i) {
                legalRange = true;
            }
        }

        //If not legel number of zeroes, return null
        if (!legalRange) {
            return false;
        }

        //Check that there are (r - 2) 0's before 1's
        int bound = currIndex - (r - 2);
        for (int i = currIndex; i > bound; i--) {
            if (currLabel.charAt(i) == '0') {
                currIndex--;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 0^i 1 x 0^(r - 2) 1^[(n - r)/2] for some i in [0...r-3].
     */
    private boolean checkType6_1() {
        int counter = 0;  //counter used to keep track of number of characters
        int currIndex = 0; //start at beginning of string for prefix

        //bounds for i
        int iLower = 0;
        int iUpper = r - 3;

        /**
         * Check
         * prefix------------------------------------------------------------
         */
        //Count the number of 0's in front of prefix
        for (int i = currIndex; i < currLabel.length(); i++) {
            if (currLabel.charAt(i) == '0') {
                counter++;
                currIndex = i + 1; //move up an index
            } else {
                break;
            }
        }

        //Check that there are i number of 0's
        boolean legalRange = false;
        for (int i = iLower; i <= iUpper && !legalRange; i++) {
            if (counter == i) {
                legalRange = true;
            }
        }

        //Check to see if there is a 1 after 0's
        if (currLabel.charAt(currIndex) != '1') {
            return false;
        }

        /**
         * Check
         * suffix-------------------------------------------------------------
         */
        //change current index to the end of the label
        currIndex = currLabel.length() - 1;

        //check that there are (n - r)/2 1's at end of label
        int bound = currIndex - ((n - r) / 2);
        for (int i = currIndex; i > bound; i--) {
            if (currLabel.charAt(i) == '1') {
                currIndex--;
            } else {
                return false;
            }
        }

        //check that there are (r - 2) 0's before 1's
        bound = currIndex - (r - 2);
        for (int i = currIndex; i > bound; i--) {
            if (currLabel.charAt(i) == '0') {
                currIndex--;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     *  0^i 1 x 0^{r-2} 1^{(n-r)/2} 0^j for some i \in [0..r-4] with j \in [1..r-i-3]
     */
    private boolean checkType7_1(){
        int counter = 0;  //counter used to keep track of number of characters
        int currIndex = 0; //start at beginning of string for prefix
        int iVal = 0; //Value for i used to calculate bounds for j

        //bounds for i and j
        int iLower = 0;
        int iUpper = r - 4;
        int jLower; //we need the value of i to calculate these
        int jUpper;

        /**
         * Check
         * prefix------------------------------------------------------------
         */
        //Count the number of 0's in front of prefix
        for (int i = currIndex; i < currLabel.length(); i++) {
            if (currLabel.charAt(i) == '0') {
                counter++;
                currIndex = i + 1; //move up an index
            } else {
                break;
            }
        }

        //Check that there are i number of 0's
        boolean legalRange = false;
        for (int i = iLower; i <= iUpper && !legalRange; i++) {
            if (counter == i) {
                iVal = i; //Set value of i used to get the bounds of j
                legalRange = true;
            }
        }

        //Check to see if there is a 1 after 0's
        if (currLabel.charAt(currIndex) != '1') {
            return false;
        }

        /**
         * Check
         * suffix-------------------------------------------------------------
         */
        jLower = 1;
        jUpper = r - iVal - 3;

        //change current index to the end of the label
        currIndex = currLabel.length() - 1;

        //Count the number of 0's at the end of the label
        for (int i = currIndex; i >= 0; i--) {
            if (currLabel.charAt(i) == '0') {
                counter++;
                currIndex--;
            } else {
                break;
            }
        }

        //Check to see if the number of zeros is within range of i in equation
        legalRange = false;
        for (int i = jLower; i <= jUpper && !legalRange; i++) {
            if (counter == i) {
                legalRange = true;
            }
        }

        //check that there are (n - r)/2 1's at end of label
        int bound = currIndex - ((n - r) / 2);
        for (int i = currIndex; i > bound; i--) {
            if (currLabel.charAt(i) == '1') {
                currIndex--;
            } else {
                return false;
            }
        }

        //check that there are (r - 2) 0's before 1's
        bound = currIndex - (r - 2);
        for (int i = currIndex; i > bound; i--) {
            if (currLabel.charAt(i) == '0') {
                currIndex--;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Create the j-string needed for compatibility check in type 3. Given the
     * value j, return the string with holes on right side of equation.
     */
    private String buildJString(int j) {
        String str = "";
        int numHoles = (n - r - (2 * j)) / 2;
        int numFirst0 = (r - 2);
        int num1 = (n - r) / 2;
        int numSecond0 = j + 1;

        for (int i = 0; i < numHoles; i++) {
            str += "H";
        }

        for (int i = 0; i < numFirst0; i++) {
            str += "0";
        }

        for (int i = 0; i < num1; i++) {
            str += "1";
        }

        for (int i = 0; i < numSecond0; i++) {
            str += "0";
        }

        return str;
    }
}
