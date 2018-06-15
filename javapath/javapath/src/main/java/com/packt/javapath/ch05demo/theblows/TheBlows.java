package com.packt.javapath.ch05demo.theblows;

public class TheBlows {
    public static TheBlows BILL = new TheBlows("Bill", "father", 42);
    public static TheBlows BECKY = new TheBlows("Becky", "mother", 37);
    public static TheBlows BEE = new TheBlows("Bee", "daughter", 5);
    public static TheBlows BOB = new TheBlows("Bob", "son", 3);
    private String name, relation, hobby = "biking";
    private int age;

    private TheBlows(String name, String relation, int age) {
        this.name = name;
        this.relation = relation;
        this.age = age;
    }

    public String getName() { return name; }

    public String getRelation() { return relation; }

    public int getAge() { return age; }

    public String getHobby() { return hobby; }

    public void setHobby(String hobby) { this.hobby = hobby; }
}
