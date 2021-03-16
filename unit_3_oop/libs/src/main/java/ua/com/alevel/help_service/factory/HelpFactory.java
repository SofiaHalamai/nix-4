package ua.com.alevel.help_service.factory;

import org.reflections.Reflections;
import ua.com.alevel.help_service.HelperService;

import java.util.Set;

public class HelpFactory {

    private static HelpFactory instance;
    private Reflections reflections;
    private Set<Class<? extends HelperService>> helpServices;

    private HelpFactory() {
        reflections = new Reflections("ua.com.alevel");
        helpServices = reflections.getSubTypesOf(HelperService.class);
    }

    public static HelpFactory getInstance() {
        if (instance == null) {
            instance = new HelpFactory();
        }
        return instance;
    }

    public HelperService getHelpService() {
        for (Class<? extends HelperService> helpService : helpServices) {
            if (!helpService.isAnnotationPresent(Deprecated.class)) {
                try {
                    return helpService.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("Library usage error!");
                }
            }
        }
        throw new RuntimeException("Library usage error!");
    }
}
