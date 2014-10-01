package Block.Utils.habbo;

/**
 * Created by luis on 25/07/14.
 */
public class HabboFilter {

    public static String getCharFilter(String data) {
        String output = "";
        for (char o : data.toCharArray())
        {
            int C = o;
            if (C < 14)
            {
                output += "[" + C + "]";
            }
            else
            {
                output += o;
            }
        }
        return output;
    }
}
