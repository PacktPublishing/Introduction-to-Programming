package com.packt.javapath.ch05demo.thejohns;

public class TheJohns {
    public static TheJohns JOE = new TheJohns("Joe", "father", 42);
    public static TheJohns JOAN = new TheJohns("Joan", "mother", 37);
    public static TheJohns JILL = new TheJohns("Jill", "daughter", 5);
    private String name, relation, hobby = "joggling";
    private int age;

    private TheJohns(String name, String relation, int age) {
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
