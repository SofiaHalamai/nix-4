package ua.com.alevel.secondTask;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ua.com.alevel.controller.MainController.ANSI_BLUE;
import static ua.com.alevel.controller.MainController.ANSI_RESET;

public class FindingFirstUniqueName {

    //В этом алгоритме мы проходим по всем элементам List
    //с помощью цикла от 0 до (N-1), N => size of List
    //Также поскольку внутренних циклов нет -> общее количество итераций цикла равно N.
    //Добавление в Set -> O(1), добавление в List -> O(N), удаление из List -> O(N).
    //Это определяет общую сложность алгоритма O(N), зависит от количесва имен в List.
    public static String findUniqueName (List<String> names){
        Set<String> setOfNames = new HashSet<>();
        List<String> result = new ArrayList<>();
        for (String name : names) {
            if (setOfNames.add(name))
                result.add(name);
            else result.remove(name);
        }
        //Сложность доступа к элементу из List -> O(1)
        System.out.println(ANSI_BLUE + "THE RESULT OF FINDING FIRST UNIQUE NAME:  " + ANSI_RESET + result.get(0));
        return result.get(0);
    }
}
