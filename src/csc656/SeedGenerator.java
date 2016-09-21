package csc656;

/**
 * Generate a seed.
 */
public class SeedGenerator {

    /**
     * Generate a seed, first calculating if n - r is odd or even.
     * @param n length of subwords, integer >= 2.
     * @param h number of holes in the seed
     * @return the seed as a String.
     *         'H' characters in returned String represent holes in the seed.
     */
    public String generateSeed(int n, int h){
        int r = n - h;

        //Check for valid inputs.
        if(n < 2)
            return null;

        if(r < 2)
            return null;

        // n - r is even
        if((n - r) % 2 == 0){
            return generateForEven(n, r);
        }else //odd
            return generateForOdd(n, r);
    }

    /**
     * Generate a seed using the following equation when n - r is even.
     * 0^(n-2)1H^(n-r)0^(r-2)1[(n-r)/2]0[(n-r+2)/2)]
     * n = h + r , where h is the number of holes in the seed.
     * @return the seed generating by inputting n and r into equation.
     */
    private String generateForEven(int n, int r){
        String seed;
        String hole = "H";
        String zero = "0";
        String one = "1";

        seed = addCharacters(zero, n - 2) + one + addCharacters(hole, n - r) + addCharacters(zero, r - 2) +
                addCharacters(one, (n - r)/2) + addCharacters(zero, (n - r + 2)/2);

        return seed;
    }

    /**
     * Generate a seed using the following equation when n - r is odd.
     * 0^(n-2)1H^(n-r)0^(r-2)1[(n-r+1)/2]0[(n-r+1)/2)]
     * n = h + r , where h is the number of holes in the seed.
     * @return the seed generating by inputting n and r into equation.
     */
    private String generateForOdd(int n, int r){
        String seed;
        String hole = "H";
        String zero = "0";
        String one = "1";

        seed = addCharacters(zero, n - 2) + one + addCharacters(hole, n - r) + addCharacters(zero, r - 2) +
                addCharacters(one, (n - r + 1)/2) + addCharacters(zero, (n - r + 1)/2);

        return seed;
    }

    /**
     * Create a String containing a consecutive number of the provided character.
     * @param chtr Character contained in the String.
     * @param num Number of characters in string.
     * @return String of given characters.
     */
    private String addCharacters(String chtr, int num){
        String str = "";

        for(int i = 0; i < num; i++){
            str += chtr;
        }

        return str;
    }
}