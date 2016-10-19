package csc656;

public class Table3 {
    
    int n;
    int r; //n - h
    String label;

    public Table3(String label, int numHoles){
        this.label = label;
        n = label.length() + 1;
        r = n - numHoles;
    }
    
    public Table3Output getType()
    {
        Table3Output result = new Table3Output();
        if(checkType1())
        {
            result.setIn(1);
            result.setType(1);
            int [] degree = {1,0};
            result.setDegree(degree);
        }
        return result;
    }
    
    private boolean checkType1()
    {
        return checkType1_1() || checkType1_2() || checkType1_3() || checkType1_4();
    }
    
    private boolean checkType1_1()
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
            return false;
        }
        if(label.charAt(i+1) == '1' && label.charAt(label.length() - 1) == '0')
        {
            return true;
        }
        return false;
    }
    
    
    private boolean checkType1_2()
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
            return false;
        }
        if(label.charAt(i+1) == '1' && 
           label.charAt(label.length() - 1) == '1' && 
           label.substring(i+2, label.length() -2) != Utils.power("1", n - i - 3))
        {
            for (int k = 0 ; k <= n-r-i-1 ; k++)
            {
                if(Utils.suffex(label.substring(i+1, label.length()-2), r-2+k) == (Utils.power("0", r-2) + Utils.power("1", k)))
                {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    
    private boolean checkType1_3()
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
            return false;
        }
        if(label.charAt(i+1) == '1' && 
           label.charAt(label.length() - 1) == '1')
        {
            for (int k = 0 ; k <= Math.min(n-r-i-1,(n-r-2)/2) ; k++)
            {
                if(Utils.suffex(label.substring(i+1, label.length()-2), r-2+k) == (Utils.power("0", r-2) + Utils.power("1", k)))
                {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    
    private boolean checkType1_4()
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
            return false;
        }
        if(label.charAt(i+1) == '1' && 
           label.charAt(label.length() - 1) == '1' && 
           label.substring(i+2, label.length() -2) != Utils.power("1", (n-r-2)/2) + Utils.power("0",(n+r-2*i-4)/2))
        {
            for (int k = 0 ; k <= n-r-2*i-2 ; k++)
            {
                if(Utils.suffex(label.substring(i+1, label.length()-2), r-2+k) == (Utils.power("0", r-2) + Utils.power("1", (n-r)/2) + Utils.power("0", k)))
                {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    
}
