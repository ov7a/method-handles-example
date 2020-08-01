package org.example;

import java.lang.reflect.Method;

public class ModifiedByInheritance extends Child {
    @Override
    String exampleMethod() {
        String firstPart = "modified return ";
        String secondPart = super.exampleMethod(); //oops
        //String thirdPart = anotherPrivateMethod(); //need to access it anyway
        String thirdPart = callAnotherPrivateMethod();
        return firstPart + secondPart + thirdPart;
    }

    String callAnotherPrivateMethod() {
        try {
            Method method = Child.class.getDeclaredMethod("anotherPrivateMethod");
            method.setAccessible(true);
            return (String) method.invoke(this);
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong", e);
        }
    }
}
