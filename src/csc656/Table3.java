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
    
}
