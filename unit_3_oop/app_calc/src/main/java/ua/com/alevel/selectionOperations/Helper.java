package ua.com.alevel.selectionOperations;

import ua.com.alevel.consoleHelper.ConsoleHelper;
import ua.com.alevel.consoleHelper.factory.HelpFactory;
import ua.com.alevel.operations.CalculationOperations;

public class Helper {

    private final ConsoleHelper consoleHelper = HelpFactory.getInstance().getHelpService();

    public void run() {
        CalculationOperations calculationOperations = new CalculationOperations();
        while (true) {
            switch (consoleHelper.dataInput("\n1. Operation sqrt(x)\n" +
                    "2. Operation summation (x + y)\n" +
                    "3. Operation subtraction (x - y)\n" +
                    "4. Operation multiplication (x * y)\n" +
                    "5. Operation division (x / y)\n" +
                    "6. Exit\n")) {
                case "1":
                    calculationOperations.sqrt();
                    break;
                case "2":
                    calculationOperations.sum();
                    break;
                case "3":
                    calculationOperations.subtract();
                    break;
                case "4":
                    calculationOperations.multiply();
                    break;
                case "5":
                    calculationOperations.divide();
                    break;
                case "6":
                    System.exit(0);
                default:
                    System.out.println("Select one of the suggested points");
                    break;
            }
        }
    }
}
