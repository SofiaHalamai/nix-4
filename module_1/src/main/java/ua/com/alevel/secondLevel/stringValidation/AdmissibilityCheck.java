package ua.com.alevel.secondLevel.stringValidation;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class AdmissibilityCheck {

    public String checkOfBracket(String checkedString) {
        char[] symbols = checkedString.toCharArray();
        boolean tmpCheckPresenceOfBrackets = false;

        Stack<Character> stack = new Stack<>();
        int counter = 0;
        List<Integer> opened = new LinkedList<>();
        List<Integer> closed = new LinkedList<>();

        for (char c : symbols) {
            counter++;
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
                opened.add(counter);
                tmpCheckPresenceOfBrackets = true;
            }
            if (c == ')' || c == '}' || c == ']') {
                if (!stack.isEmpty()) {
                    char fromStack = stack.peek();
                    if ((c == ')' && fromStack == '(') ||
                            (c == '}' && fromStack == '{') ||
                            (c == ']' && fromStack == '[')) {
                        stack.pop();
                        ((LinkedList<Integer>) opened).removeLast();
                    } else {
                        closed.add(counter);
                        break;
                    }
                } else {
                    stack.add(c);
                    closed.add(counter);
                    break;
                }
                tmpCheckPresenceOfBrackets = true;
            }
        }
        if(!stack.isEmpty()) {
            if(!closed.isEmpty()) {
                return "Entered string doesn't meet the condition: error in index numbered: " + ((LinkedList<Integer>) closed).getFirst();
            } else {
                if(!opened.isEmpty()) {
                    return "Entered string doesn't meet the condition: error in index numbered: " + ((LinkedList<Integer>) opened).getFirst();
                }
            }
        }
        if (!tmpCheckPresenceOfBrackets)
            return "Entered string doesn't meet the condition: any brackets are missing";
        else {
            return "Entered string satisfies the condition";
        }
    }
}
