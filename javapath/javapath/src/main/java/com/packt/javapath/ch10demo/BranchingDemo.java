package com.packt.javapath.ch10demo;

import java.util.List;
import java.util.Objects;

public class BranchingDemo {
    public static void main(String... args) {

        breakDemo();
        findPerson();
        breakWithLabelDemo();
        continueDemo();
        continueWithLabelDemo();
    }


    public static void continueWithLabelDemo(){
        System.out.println();
        int[][][] data = {
                {{1,1,2},{0,3,0},{2,4,1},{2,3,2}},
                {{0,2,0},{1,3,4},{2,0,1},{2,2,2}}};
        int threshold = 4;
        int x = 0, y;
        for(int[][] dd: data){
            y = 0;
            cont: for(int[] d: dd){
                int sum = 0;
                for(int i: d){
                    if(i == 1){
                        y++;
                        continue cont;
                    }
                    sum += i;
                }
                if(sum >= threshold){
                    System.out.println("sum=" + sum + ", x=" + x + ", y=" + y);
                }
                y++;
            }
            x++;
        }
    }

    public static void continueDemo(){
        System.out.println();
        int[][][] data = {
                {{1,1,2},{0,3,0},{2,4,1},{2,3,2}},
                {{0,2,0},{1,3,4},{2,0,1},{2,2,2}}};
        int threshold = 4;
        int x = 0, y;
        for(int[][] dd: data){
            y = 0;
            for(int[] d: dd){
                int sum = 0;
                for(int i: d){
                    if(i == 1){
                        continue;
                    }
                    sum += i;
                }
                if(sum >= threshold){
                    System.out.println("sum=" + sum + ", x=" + x + ", y=" + y);
                }
               y++;
            }
            x++;
        }
    }

    public static void breakWithLabelDemo() {
        int[][][] data = {
                {{1,0,2},{1,2,0},{2,1,0},{0,3,0}},
                {{1,1,1},{1,3,0},{2,0,1},{1,0,1}}};
        int threshold = 4;
        int x = 0, y = 0;
        boolean isFound = false;
        exit: for(int[][] dd: data){
            y = 0;
            for(int[] d: dd){
                int sum = 0;
                for(int i: d){
                    sum += i;
                    if(sum >= threshold){
                        isFound = true;
                        break exit;
                    }
                }
                y++;
            }
            x++;
        }
        System.out.println("isFound=" + isFound + ", x=" + x + ", y=" + y); //prints: isFound=true, x=1, y=1
    }

    private static void findPerson2(){
        List<Person> list = List.of(new Teacher(32, "Joe", "History"),
                new Student(29,"Joe", 4),
                new Student(28,"Jill", 3),
                new Teacher(33, "ALice", "Maths"));
        Person personOfInterest = new Person(29,"Joe");
        findPerson(list, personOfInterest);
        return;
    }

    private static void findPerson2(List<Person> list, Person personOfInterest){
        for (Person p: list){
            System.out.println(p);
            if(p.equals(personOfInterest)){
                System.out.println("Found: " + p);
                return;
            }
        }
        System.out.println("Not found: " + personOfInterest);
        return;
    }

    private static void findPerson(){
        List<Person> list = List.of(new Teacher(32, "Joe", "History"),
                new Student(29,"Joe", 4),
                new Student(28,"Jill", 3),
                new Teacher(33, "ALice", "Maths"));
        Person personOfInterest = new Person(29,"Joe");
        Person person = findPerson(list, personOfInterest);
        if(person == null){
            System.out.println("Not found: " + personOfInterest);
        } else {
            System.out.println("Found: " + person);
        }
    }

    private static Person findPerson(List<Person> list, Person personOfInterest){
        for (Person p: list){
            System.out.println(p);
            if(p.equals(personOfInterest)){
                return p;
            }
        }
        return null;
    }

    private static Person findPerson1(List<Person> list, Person personOfInterest){
        Person person = null;
        for (Person p: list){
            System.out.println(p);
            if(p.equals(personOfInterest)){
                person = p;
                break;
            }
        }
        return person;
    }

    public static void breakDemo(){
        System.out.println();
        List<Person> list = List.of(new Teacher(32, "Joe", "History"),
                new Student(29,"Joe", 4),
                new Student(28,"Jill", 3),
                new Teacher(33, "ALice", "Maths"));
        Person personOfInterest = new Person(30,"Joe");
        Person person = null;
        for (Person p: list){
            System.out.println(p);
            if(p.equals(personOfInterest)){
                person = p;
                break;
            }
        }
        if(person == null){
            System.out.println("Not found: " + personOfInterest);
        } else {
            System.out.println("Found: " + person);
        }
        System.out.println();
        int[][][] data = {
                {{1,0,2},{1,2,0},{2,1,0},{0,3,0}},
                {{1,1,1},{1,3,0},{2,0,1},{1,0,1}}};
        int threshold = 4;
        int x = 0, y = 0;
        boolean isFound = false;
        for(int[][] dd: data){
            y = 0;
            for(int[] d: dd){
                int sum = 0;
                for(int i: d){
                    sum += i;
                    if(sum >= threshold){
                        isFound = true;
                        break;
                    }
                }
                if(isFound){
                    break;
                }
                y++;
            }
            if(isFound){
                break;
            }
            x++;
        }
        System.out.println("isFound=" + isFound + ", x=" + x + ", y=" + y);

    }

    private static class Person{
        private int age;
        private  String name;

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            Person person = (Person) o;
            return age == person.age &&
                    Objects.equals(name, person.name);
        }

        @Override
        public String toString() {
            return "Person{age=" + age +
                    ", name='" + name + "'}";
        }
    }

    private static class Student extends Person {
        private int year;

        public Student(int age, String name, int year) {
            super(age, name);
            this.year = year;
        }

        @Override
        public String toString() {
            return "Student{year=" + year +
                    ", " + super.toString() + "}";
        }
    }

    private static class Teacher extends Person {
        private String subject;

        public Teacher(int age, String name, String subject) {
            super(age, name);
            this.subject = subject;
        }
        @Override
        public String toString() {
            return "Teacher{subject=" + subject +
                    ", " + super.toString() + "}";
        }
    }
}
