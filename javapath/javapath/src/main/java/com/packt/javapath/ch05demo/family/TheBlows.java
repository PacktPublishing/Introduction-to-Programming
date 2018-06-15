package com.packt.javapath.ch05demo.family;

public class TheBlows extends Family {
    public static TheBlows BILL = new TheBlows("Bill", "father", 42);
    public static TheBlows BECKY = new TheBlows("Becky", "mother", 37);
    public static TheBlows BEE = new TheBlows("Bee", "daughter", 5);
    public static TheBlows BOB = new TheBlows("Bob", "son", 3);
    private TheBlows(String name, String relation, int age) {
        super(name, relation, age, "biking");
    }
}
