package ua.com.alevel.calculator.factory;

import ua.com.alevel.calculator.Calculator;
import org.reflections.Reflections;

import java.util.Set;

public class CalcFactory {

    private static CalcFactory instance;
    private Reflections reflections;
    private Set<Class<? extends Calculator>> calculator;

    private CalcFactory() {
        reflections = new Reflections("ua.com.alevel");
        calculator = reflections.getSubTypesOf(Calculator.class);
    }

    public static CalcFactory getInstance() {
        if (instance == null) {
            instance = new CalcFactory();
        }
        return instance;
    }

    public Calculator getCalcService() {
        for (Class<? extends Calculator> calc : calculator) {
            if (!calc.isAnnotationPresent(Deprecated.class)) {
                try {
                    return calc.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("Library usage error!");
                }
            }
        }
        throw new RuntimeException("Library usage error!");
    }
}
