package code.other;

import org.apache.commons.lang3.*;
import java.util.Arrays;

public class Second {

    public void reverseArr() {
        int [] simpleIntArray = { 2, 4, 6, 8, 10 };
        ArrayUtils.reverse(simpleIntArray);
        System.out.println(Arrays.toString(simpleIntArray));
    }
}
