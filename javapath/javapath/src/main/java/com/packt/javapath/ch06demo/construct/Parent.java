package com.packt.javapath.ch06demo.construct;

public class Parent extends GrandDad {
    //private String name = "Parent";
    private static String NAME = Parent.class.getSimpleName();

    public Parent() {
        this("The Defaults");
        //System.out.println(NAME);
    }

    public Parent(String familyName) {
        super(familyName);
        System.out.println(familyName + ": " + NAME);
    }
}

