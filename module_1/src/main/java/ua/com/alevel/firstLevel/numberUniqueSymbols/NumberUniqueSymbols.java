package ua.com.alevel.firstLevel.numberUniqueSymbols;

import java.util.HashSet;
import java.util.Set;

public class NumberUniqueSymbols {

    public int uniqueCount (int[] arr){
        Set <Integer> setOfSymbols = new HashSet<>();
        for (int x : arr)
            setOfSymbols.add(x);
        return setOfSymbols.size();
    }
}
