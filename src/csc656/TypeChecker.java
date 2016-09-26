package csc656;

public class TypeChecker {
    
    private boolean checkType1(String label, int h)
    {
        int r, n, i, j;
        i = 0;
        n = label.length() + 1;
        r = n - h;
        
        for(j = 0 ; j < label.length() ; j++)
        {
            if(label.charAt(j) == '0')
            {
                i++;
            }
            else
            {
                break;
            }
        }
        if(!(i >= r-1 && i <= n-3))
        {
            return false;
        }
        return label.charAt(j) == '1' && label.substring(j+1).length() == n - i - 2;
    }
    
    
    
    private boolean checkType2(String label, int h)
    {
        int r, n, i, j;
        i = 0;
        n = label.length() + 1;
        r = n - h;
        
        for(j = 0 ; j < label.length() ; j++)
        {
            if(label.charAt(j) == '0')
            {
                i++;
            }
            else
            {
                break;
            }
        }
        if(!(i == r - 2))
        {
            return false;
        }
        return label.charAt(j) == '1' && label.substring(j+1).length() == n - r;
    }
}
