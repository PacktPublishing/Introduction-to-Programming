package com.packt.javapath.ch05demo.family;

public class Family {
    private String name, relation, hobby;
    private int age;

    protected Family(String name, String relation, int age, String hobby) {
        this.name = name;
        this.relation = relation;
        this.age = age;
        this.hobby = hobby;
    }

    public String getName() { return name; }

    public String getRelation() { return relation; }

    public int getAge() { return age; }

    public String getHobby() { return hobby; }

    public void setHobby(String hobby) { this.hobby = hobby; }

}
