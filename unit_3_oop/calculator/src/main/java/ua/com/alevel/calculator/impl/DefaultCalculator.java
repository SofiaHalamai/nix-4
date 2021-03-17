package ua.com.alevel.calculator.impl;

import ua.com.alevel.calculator.Calculator;

import java.math.BigDecimal;
import java.math.BigInteger;

public class DefaultCalculator implements Calculator {

    @Override
    public BigInteger squareRoot(BigInteger x) {
        return x.sqrt();
    }

    @Override
    public BigDecimal sum(BigDecimal x, BigDecimal y) {
        return x.add(y);
    }

    @Override
    public BigDecimal subtraction(BigDecimal x, BigDecimal y) {
        return x.subtract(y);
    }

    @Override
    public BigDecimal multiplication(BigDecimal x, BigDecimal y) {
        return x.multiply(y);
    }

    @Override
    public BigDecimal division(BigDecimal x, BigDecimal y) {
        return x.divide(y);
    }
}
