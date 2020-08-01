package org.example;

import java.lang.reflect.Method;

public class ModifiedByReflection extends Parent {

    @Override
    String exampleMethod() {
        Child child = new Child();
        String firstPart = "modified return ";
        String secondPart = callParentExampleMethod(child);
        String thirdPart = callAnotherPrivateMethod(child);
        return firstPart + secondPart + thirdPart;
    }

    String callAnotherPrivateMethod(Child child) {
        try {
            Method method = Child.class.getDeclaredMethod("anotherPrivateMethod");
            method.setAccessible(true);
            return (String) method.invoke(child);
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong", e);
        }
    }

    String callParentExampleMethod(Parent childAsParent) {
        try {
            Method method = Parent.class.getDeclaredMethod("exampleMethod");
            method.setAccessible(true);
            return (String) method.invoke(childAsParent);
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong", e);
        }
    }
}
