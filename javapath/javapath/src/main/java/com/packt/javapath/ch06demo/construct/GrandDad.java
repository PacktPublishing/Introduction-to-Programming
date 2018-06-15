package com.packt.javapath.ch06demo.construct;

public class GrandDad {
    //private String name = "GrandDad";
    private static String NAME = GrandDad.class.getSimpleName();
    public GrandDad() {
        this("The Defaults");
        //System.out.println(NAME);

    }
    public GrandDad(String familyName) {
        System.out.println(familyName + ": " + NAME);
    }

}
