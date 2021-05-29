package ua.com.alevel.workWithProperties;

import ua.com.alevel.annotation.PropertyKey;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Properties;

public class FieldInitializer {

    public <T> T assignmentOfValues(Class<T> tClass, Properties properties) {
        Constructor c = getConstructor(tClass);
        T current;
        String property;
        try {
            current = (T) Objects.requireNonNull(c).newInstance();

            for (Field field : tClass.getFields()) {
                if (field.isAnnotationPresent(PropertyKey.class)) {
                    PropertyKey key = field.getAnnotation(PropertyKey.class);
                    property = properties.getProperty(key.value());
                    String type = field.getType().getName();
                    if (int.class.getName().equals(type) || Integer.class.getName().equals(type)) {
                        field.set(current, Integer.parseInt(property));
                    } else if (long.class.getName().equals(type) || Long.class.getName().equals(type)) {
                        field.set (current, Long.parseLong(property));
                    } else if (double.class.getName().equals(type) || Double.class.getName().equals(type)) {
                        field.set (current, Double.parseDouble(property));
                    } else if (char.class.getName().equals(type) || Character.class.getName().equals(type)) {
                        field.set (current, property.charAt(0));
                    } else if (boolean.class.getName().equals(type) || Boolean.class.getName().equals(type)) {
                        field.set (current, Boolean.parseBoolean(property));
                    } else if (String.class.getName().equals(type)) {
                        field.set(current, property);
                    } else {
                        System.out.println("The type of the variable present in the class is not supported by the program");
                    }
                }
            }
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            System.out.println("Error getting class information");
            return null;
        }
        return current;
    }

    private <T> Constructor getConstructor(Class<T> tClass) {
        Constructor[] constructors = tClass.getDeclaredConstructors();
        Constructor constr = null;
        for (Constructor constructor : constructors) {
            constr = constructor;
            if (constr.getGenericParameterTypes().length == 0)
                break;
        }
        return constr;
    }
}
