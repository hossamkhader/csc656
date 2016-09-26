package csc656;

public class TypeChecker {

    private boolean checkType1(String label, int h) {
        int r, n, i, j;
        i = 0;
        n = label.length() + 1;
        r = n - h;

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

    private boolean checkType2(String label, int h) {
        int r, n, i, j;
        i = 0;
        n = label.length() + 1;
        r = n - h;

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
     * Method checks if vertex is of type 3 as per:
     * 0^(i) 1 @^(n-r) 0^(r-i-2) for some i in [0..r-3]
     * @param label label of the vertex being typed
     * @param h number of holes in seed
     * @return 
     */
    private boolean checkType3(String label, int h) {
        // the variable m is used to count suffix 0's
        int r, n, i, j, m;
        m = 0;
        i = 0;
        n = label.length() - 1;
        r = n - h;
        
        //get count of 0's and see if within range, also check for following 1 
        for (j = 0; j < label.length(); j++) {
            if (label.charAt(j) == '0') {
                i++;
            } else {
                break;
            }
        }
        if (!(i >= 0 && i <= r - 3 && j == 1)) {
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
     * Method checks if vertex is of type 4 as per:
     * @^(n-r) 0^(r-2) 1 
     * @param label label of the vertex being typed
     * @param h number of holes in seed
     * @return 
     */
    private boolean checkType4(String label, int h) {
        int r, n, j;
        n = label.length() - 1;
        r = n - h;
        
        //begins counting 0's after @'s, verifying their number
        for (j = (n - r); j < (n - r) + (r - 2); j++) {
            if (label.charAt(j) != '0') {
                return false;
            }
        }
        //verfies that the final char is 1
        return (j == label.length() - 1 && label.charAt(j) == 1);
    }

    /**
     * Method checks if vertex is of type 5 as per:
     * @^(n-r-i+1) 0^(r-2) 1^i for some i in [2..(n-r-2)/2] 
     * unlike similar methods, this one is checked in reverse to more easily 
     * accommodate the suffix 
     * @param label label of the vertex being typed
     * @param h number of holes in seed
     * @return 
     */
    private boolean checkType5(String label, int h) {
        //variable m is used to track the number of 0's
        int r, n, j, i, m;
        i = 0;
        n = label.length() - 1;
        r = n - h;
        
        //gets count of 1's in suffix and verifies within range 
        for (j = label.length() - 1; j <= 0; j--) {
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
}
