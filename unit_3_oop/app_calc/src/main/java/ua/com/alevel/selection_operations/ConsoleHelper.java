package ua.com.alevel.selection_operations;

import ua.com.alevel.help_service.HelperService;
import ua.com.alevel.help_service.factory.HelpFactory;
import ua.com.alevel.operations.Calculator;

public class ConsoleHelper {
    private final HelperService helperService = HelpFactory.getInstance().getHelpService();

    public void run() {
        Calculator calculator = new Calculator();
        while (true) {
            switch (helperService.dataInput("\n1. Operation sqrt(x)\n" +
                    "2. Operation summation (x + y)\n" +
                    "3. Operation subtraction (x - y)\n" +
                    "4. Operation multiplication (x * y)\n" +
                    "5. Operation division (x / y)\n" +
                    "6. Exit\n")) {
                case "1":
                    calculator.sqrt();
                    break;
                case "2":
                    calculator.sum();
                    break;
                case "3":
                    calculator.subtract();
                    break;
                case "4":
                    calculator.multiply();
                    break;
                case "5":
                    calculator.divide();
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
