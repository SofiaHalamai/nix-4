package ua.com.alevel.util;

public class ConvertStringToIntUtil {

    public static int convert(String value) {
        try {
            int converted = Integer.parseInt (value);
            return converted;
        } catch (Exception e) {
            System.out.println("Exception = " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
