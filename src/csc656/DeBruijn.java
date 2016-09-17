package csc656;

public class DeBruijn
{
    public static String getSequence (int order) {

        String deBruijn = "";
        for (int i = 0; i < order; i++)
        {
            deBruijn = deBruijn + "0";
        }

        for (int i = order; i < (1 << order); i++)
        {
            String suffix = deBruijn.substring(i - order + 1);
            if (!deBruijn.contains(suffix + "1"))
            {
                deBruijn = deBruijn + "1";
            }
            else
            {
                deBruijn = deBruijn + "0";
            }
        }
        return deBruijn;
    }
}
