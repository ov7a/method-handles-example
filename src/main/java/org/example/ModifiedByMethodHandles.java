package org.example;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class ModifiedByMethodHandles extends Parent {
    @Override
    String exampleMethod() {
        Child child = new Child();
        String firstPart = "modified return ";
        String secondPart = callSuperExampleMethod(child);
        String thirdPart = callAnotherPrivateMethod(child);
        return firstPart + secondPart + thirdPart;
    }

    private MethodHandles.Lookup getMethodLookup() throws IllegalAccessException {
        return MethodHandles.privateLookupIn(Child.class, MethodHandles.lookup());
    }

    private String callSuperExampleMethod(Child child) {
        try {
            MethodHandles.Lookup lookup = getMethodLookup();
            MethodHandle baseHandle = lookup.findSpecial(Parent.class, "exampleMethod", MethodType.methodType(String.class), Child.class);
            return (String) baseHandle.invoke(child);
        } catch (Throwable e) {
            throw new RuntimeException("Something went wrong", e);
        }
    }

    private String callAnotherPrivateMethod(Child child) {
        try {
            MethodHandles.Lookup lookup = getMethodLookup();
            MethodHandle baseHandle = lookup.findVirtual(Child.class, "anotherPrivateMethod", MethodType.methodType(String.class));
            return (String) baseHandle.invoke(child);
        } catch (Throwable e) {
            throw new RuntimeException("Something went wrong", e);
        }
    }
}
