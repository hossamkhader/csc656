package csc656;

public class Utils {
    
    public static String power(String s, int n){
        
        String tmp = null;
        for(int i = 0 ; i < n ; i++){
            tmp += s;
        }
        return tmp;
    }
}
