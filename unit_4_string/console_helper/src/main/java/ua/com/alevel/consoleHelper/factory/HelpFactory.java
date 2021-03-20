package ua.com.alevel.consoleHelper.factory;

import org.reflections.Reflections;
import ua.com.alevel.consoleHelper.ConsoleHelper;

import java.util.Set;

public class HelpFactory {

    private static HelpFactory instance;
    private Reflections reflections;

    private Set<Class<? extends ConsoleHelper>> consoleHelper;

    private HelpFactory() {
        reflections = new Reflections("ua.com.alevel");
        consoleHelper = reflections.getSubTypesOf(ConsoleHelper.class);
    }

    public static HelpFactory getInstance() {
        if (instance == null) {
            instance = new HelpFactory();
        }
        return instance;
    }

    public ConsoleHelper getHelpService() {
        for (Class<? extends ConsoleHelper> helper : consoleHelper) {
            if (!helper.isAnnotationPresent(Deprecated.class)) {
                try {
                    return helper.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("Library usage error!");
                }
            }
        }
        throw new RuntimeException("Library usage error!");
    }
}
