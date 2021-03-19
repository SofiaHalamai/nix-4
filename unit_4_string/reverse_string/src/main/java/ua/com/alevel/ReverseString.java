package ua.com.alevel;

public class ReverseString {

    public static String reverse (String src){
        String splitString [] = src.split("\\s");
        String reverseString = "";
        for (String splitStr: splitString) {
            src = splitStr;
            String tmpReverseString = "";
            for (int i = 0; i < src.length(); i++) {
                tmpReverseString = src.charAt(i) + tmpReverseString;
            }
            reverseString += tmpReverseString + " ";
        }
        return reverseString.trim();
    }


    public static String reverse (String src, String dest){
        String reverseDest = reverse(dest);
        String reverseString = src.replaceAll(dest, reverseDest);
        return reverseString;
    }

    public static String reverse (String src, int firstIndex, int lastIndex){
        int stringLength = src.length();
        if (!checkCorrectIndex(stringLength, firstIndex, lastIndex)) return "Incorrect string index";
        String reverseSubstring = src.substring(0, firstIndex) + reverse(src.substring(firstIndex, lastIndex+1)) + src.substring(lastIndex+1, stringLength);

        return reverseSubstring;
    }

    public static boolean checkCorrectIndex(int length, int firstIndex, int lastIndex) {
        if (firstIndex >= lastIndex || lastIndex > (length - 1))
            return false;
        else
            return true;
    }
}
