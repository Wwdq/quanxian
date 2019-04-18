package common;
public class LevelUtil {
    private static String root="0";
    private static String temp=".";

    public static String getLevel(String  level,Integer  id){

        if(level==null||level=="")
            return root;
        else
            return level +temp+id;
    }
}
