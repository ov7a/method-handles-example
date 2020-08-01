package org.example;

public class Child extends Parent {
    @Override
    String exampleMethod() {
        String firstPart = privateMethodToModify();
        String secondPart = super.exampleMethod();
        String thirdPart = anotherPrivateMethod();
        return firstPart + secondPart + thirdPart;
    }

    private String privateMethodToModify() {
        return "this is a child addition to return ";
    }

    private String anotherPrivateMethod() {
        return " :)";
    }
}
