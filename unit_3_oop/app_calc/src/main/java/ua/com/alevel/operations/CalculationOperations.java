package ua.com.alevel.operations;

import ua.com.alevel.calculator.Calculator;
import ua.com.alevel.calculator.factory.CalcFactory;
import ua.com.alevel.console_helper.ConsoleHelper;
import ua.com.alevel.console_helper.factory.HelpFactory;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CalculationOperations {

    private final Calculator calculator = CalcFactory.getInstance().getCalcService();
    private final ConsoleHelper consoleHelper = HelpFactory.getInstance().getHelpService();

    public void sqrt(){
        BigInteger x = new BigInteger(consoleHelper.dataInput("Operation sqrt(x)"));
        if (x.compareTo(BigInteger.valueOf(0)) == -1) {
            x = new BigInteger(consoleHelper.dataInput("Error! Root of a negative number! Try again!"));
        }

        BigInteger result_sqrt = calculator.squareRoot(x);

        consoleHelper.dataOutput(String.valueOf(result_sqrt));
    }

    public void sum() {
        BigDecimal x = new BigDecimal(consoleHelper.dataInput("Operation summation (x + y)"));
        BigDecimal y = new BigDecimal(consoleHelper.dataInput("next value..."));

        BigDecimal result_add = calculator.sum(x,y);

        consoleHelper.dataOutput(String.valueOf(result_add));
    }

    public void subtract(){
        BigDecimal x = new BigDecimal(consoleHelper.dataInput("Operation subtraction (x - y)"));
        BigDecimal y = new BigDecimal(consoleHelper.dataInput("next value..."));

        BigDecimal result_subtract = calculator.subtraction(x,y);

        consoleHelper.dataOutput(String.valueOf(result_subtract));
    }

    public void multiply(){
        BigDecimal x = new BigDecimal(consoleHelper.dataInput("Operation multiplication (x * y)"));
        BigDecimal y = new BigDecimal(consoleHelper.dataInput("next value..."));

        BigDecimal result_multiply = calculator.multiplication(x,y);

        consoleHelper.dataOutput(String.valueOf(result_multiply));
    }

    public void divide(){
        BigDecimal x = new BigDecimal(consoleHelper.dataInput("Operation division (x / y)"));
        BigDecimal y = new BigDecimal(consoleHelper.dataInput("next value..."));
        if (y.compareTo(BigDecimal.valueOf(0)) == 0) {
            y = new BigDecimal(consoleHelper.dataInput("Division by zero! Try again!"));
        }

        BigDecimal result_divide = calculator.division(x,y);

        consoleHelper.dataOutput(String.valueOf(result_divide));
    }
}
