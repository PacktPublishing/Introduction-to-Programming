package com.packt.javapath.ch06demo.construct;

public class Child extends Parent{
    //private String name = "Child";
    private static String NAME = Child.class.getSimpleName();
    public Child() {
        this("The Defaults");
        //System.out.println(NAME);
    }
    public Child(String familyName) {
        super(familyName);
        System.out.println(familyName + ": " + NAME);
    }
}



