package org.example;

public class ModifiedByCopyPaste extends Parent {
    @Override
    String exampleMethod() {
        String firstPart = privateMethodToModify();
        String secondPart = super.exampleMethod();
        String thirdPart = anotherPrivateMethod();
        return firstPart + secondPart + thirdPart;
    }

    private String privateMethodToModify() {
        return "modified return ";
    }

    private String anotherPrivateMethod() {
        return " :)";
    }
}
