package ua.com.alevel.calc_service;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface CalcService {

    BigInteger squareRoot (BigInteger x);
    BigDecimal sum (BigDecimal x, BigDecimal y);
    BigDecimal subtraction (BigDecimal x, BigDecimal y);
    BigDecimal multiplication (BigDecimal x, BigDecimal y);
    BigDecimal division (BigDecimal x, BigDecimal y);
}
