package ua.com.alevel.util;

import ua.com.alevel.annotation.ValueOfCell;
import ua.com.alevel.entity.TableFromCsv;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class FieldInitializer {

    public <T> List<T> assignmentOfValues(Class<T> tClass, TableFromCsv tableFromCsv) {
        List<T> resultList = new ArrayList<>();
        Constructor c = getConstructor(tClass);
        T current;
        String property;
        for (int i = 0; i < tableFromCsv.sizeOfRows() - 1; i++) {
            try {
                current = (T) Objects.requireNonNull(c).newInstance();

                for (Field field : tClass.getDeclaredFields()) {
                    if (field.isAnnotationPresent(ValueOfCell.class)) {
                        ValueOfCell key = field.getAnnotation(ValueOfCell.class);
                        property = tableFromCsv.getCell(i, key.value());
                        field.setAccessible(true);
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
                resultList.add(current);
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
                System.out.println("Error getting class information");
                return null;
            }
        }
        return resultList;
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

