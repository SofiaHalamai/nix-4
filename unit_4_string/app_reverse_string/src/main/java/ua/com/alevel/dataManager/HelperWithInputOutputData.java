package ua.com.alevel;

import ua.com.alevel.consoleHelper.ConsoleHelper;
import ua.com.alevel.consoleHelper.factory.HelpFactory;
import ua.com.alevel.util.ConvertStringToIntUtil;

public class HelperWithInputOutputData {
    private final ConsoleHelper consoleHelper = HelpFactory.getInstance().getHelpService();

    public void run() {
        String inputString = consoleHelper.dataInput("Enter a string for reverse: ");
        while (true) {
            String position = consoleHelper.dataInput("\n" + "1. Task 1: Do the reverse separately for each word" + "\n" +
                    "2. Task 2: Do the reverse in the specified word" + "\n" +
                    "3. Task 3: Do the reverse in the specified character interval" + "\n" +
                    "4. Exit" + "\n" + "Select one of the points");
            switch (position) {
                case "1":
                    reverseEachWord(inputString);
                    break;
                case "2":
                    reverseSpecifiedWord(inputString);
                    break;
                case "3":
                    reverseSpecifiedCharacterInterval(inputString);
                    break;
                case "4":
                    System.exit(0);
                default:
                    consoleHelper.dataOutput("Select one of the suggested points");
                    break;
            }
        }
    }

    public void reverseEachWord(String inputString){
        consoleHelper.dataOutput(ReverseString.reverse(inputString));
    }

    public void reverseSpecifiedWord (String inputString){
        String dest = consoleHelper.dataInput("Enter a word for reverse: ");
        consoleHelper.dataOutput(ReverseString.reverse(inputString, dest));
    }

    public void reverseSpecifiedCharacterInterval (String inputString){
        int firstIndex = ConvertStringToIntUtil.convert(consoleHelper.dataInput("Enter a char number to start: "));
        int lastIndex = ConvertStringToIntUtil.convert(consoleHelper.dataInput("Enter a char number to finish: "));
        consoleHelper.dataOutput(ReverseString.reverse(inputString, firstIndex,lastIndex));
    }
}
