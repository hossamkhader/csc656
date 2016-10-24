package csc656;

/**
 * Class Authors: Hossam Khader
 */
public class Utils {
    
    public static String power(String s, int n){
        
        String tmp = null;
        for(int i = 0 ; i < n ; i++){
            tmp += s;
        }
        return tmp;
    }
    
    
    public static String suffex(String s, int n)
    {
        if(n > s.length())
        {
            return null;
        }
        else
        {
            return s.substring(s.length() - n, s.length());
        }
    }
    
    public static boolean isCompatible(String a, String b)
    {   
        if(a.length() != b.length())
        {
            return false;
        }
        for(int i = 0; i < a.length() ; i ++)
        {
            if(a.charAt(i) == b.charAt(i) || a.charAt(i) == 'H' || b.charAt(i) == 'H')
            {
                
            }
            else
            {
                return false;
            }
        }
        return true;
    }
}
