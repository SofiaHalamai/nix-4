package ua.com.alevel.operations;

import ua.com.alevel.calc_service.CalcService;
import ua.com.alevel.calc_service.factory.CalcFactory;
import ua.com.alevel.help_service.HelperService;
import ua.com.alevel.help_service.factory.HelpFactory;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Calculator {

    private final CalcService calcService = CalcFactory.getInstance().getCalcService();
    private final HelperService helperService = HelpFactory.getInstance().getHelpService();

    public void sqrt(){
        BigInteger x = new BigInteger(helperService.dataInput("Operation sqrt(x)"));
        if (x.compareTo(BigInteger.valueOf(0)) == -1) {
            x = new BigInteger(helperService.dataInput("Error! Root of a negative number! Try again!"));
        }

        BigInteger result_sqrt = calcService.squareRoot(x);

        helperService.dataOutput(String.valueOf(result_sqrt));
    }

    public void sum() {
        BigDecimal x = new BigDecimal(helperService.dataInput("Operation summation (x + y)"));
        BigDecimal y = new BigDecimal(helperService.dataInput("next value..."));

        BigDecimal result_add = calcService.sum(x,y);

        helperService.dataOutput(String.valueOf(result_add));
    }

    public void subtract(){
        BigDecimal x = new BigDecimal(helperService.dataInput("Operation subtraction (x - y)"));
        BigDecimal y = new BigDecimal(helperService.dataInput("next value..."));

        BigDecimal result_subtract = calcService.subtraction(x,y);

        helperService.dataOutput(String.valueOf(result_subtract));
    }

    public void multiply(){
        BigDecimal x = new BigDecimal(helperService.dataInput("Operation multiplication (x * y)"));
        BigDecimal y = new BigDecimal(helperService.dataInput("next value..."));

        BigDecimal result_multiply = calcService.multiplication(x,y);

        helperService.dataOutput(String.valueOf(result_multiply));
    }

    public void divide(){
        BigDecimal x = new BigDecimal(helperService.dataInput("Operation division (x / y)"));
        BigDecimal y = new BigDecimal(helperService.dataInput("next value..."));
        if (y.compareTo(BigDecimal.valueOf(0)) == 0) {
            y = new BigDecimal(helperService.dataInput("Division by zero! Try again!"));
        }

        BigDecimal result_divide = calcService.division(x,y);

        helperService.dataOutput(String.valueOf(result_divide));
    }
}
